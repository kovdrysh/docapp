package home.test.services;

import home.test.Utils.MongoHelper;
import home.test.dao.FolderDao;
import home.test.model.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FolderService {
    @Autowired
    private FolderDao folderDao;

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
        folderDao.remove(id);
    }

}
