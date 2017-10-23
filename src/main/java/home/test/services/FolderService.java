package home.test.services;

import home.test.Utils.MongoHelper;
import home.test.dao.FolderDao;
import home.test.model.Folder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FolderService {
    @Autowired
    private FolderDao folderDao;
    @Autowired
    private DocumentService documentService;

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(FolderService.class);
    public void add(Folder folder){
        folder.setId(MongoHelper.generateId());
        folder.setDate(MongoHelper.generateCurrentDate());
        folderDao.save(folder);
    }

    public void update(Folder folder){
        folderDao.update(folder);
    }

    public Folder get(Long id){
        return folderDao.get(id);
    }

    public List<Folder> getAll(){
        return folderDao.getAll();
    }

    public List<Folder> getAllByParentId(Long parentId){
        return folderDao.getAllByParentId(parentId);
    }

    public void remove(Long id){
        List<Folder> view = getAllByParentId(id);
        List<Long> itemsIdToDelete = new ArrayList<Long>();
        itemsIdToDelete.add(id);
        while (view.size() > 0){
            Long idForLook = view.get(view.size() - 1).getId();
            itemsIdToDelete.add(view.get(view.size() - 1).getId());
            view.remove(view.size() - 1);
            view.addAll(getAllByParentId(idForLook));
        }
        documentService.bulkDelete(itemsIdToDelete);
        folderDao.bulkRemove(itemsIdToDelete);
    }




}
