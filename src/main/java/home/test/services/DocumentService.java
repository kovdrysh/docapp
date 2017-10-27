package home.test.services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import home.test.Utils.MongoHelper;
import home.test.dao.DocumentDao;
import home.test.model.Document;
import org.apache.log4j.Logger;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentDao documentDao;
    private static final Logger logger = Logger.getLogger(DocumentService.class);

    public void save(Long parentId, MultipartFile file) {
        try {
            String type = MongoHelper.parseType(file.getOriginalFilename());
            Document document = new Document(file.getInputStream(), parentId, file.getOriginalFilename(), file.getContentType(), MongoHelper.generateCurrentDate(), type);
            DBObject metaData = new BasicDBObject();
            metaData.put("objectId", MongoHelper.generateId());
            metaData.put("parentId", parentId);
            metaData.put("doctype", MongoHelper.parseType(file.getOriginalFilename()));
            metaData.put("date", MongoHelper.generateCurrentDate());
            metaData.put("createdBy", SecurityContextHolder.getContext().getAuthentication().getName());

            documentDao.save(document, metaData);
        } catch (IOException e) {
            logger.debug("Cannot create object");
        }
    }

    public List<Document> getAllByParentId(Long parentId) {
        GridFSFindIterable allbyParentId = documentDao.getAllbyParentId(parentId);
        List<Document> documents = new ArrayList<Document>();
        for (GridFSFile gridFSFile : allbyParentId) {
            String id1 = gridFSFile.getObjectId().toHexString();
            Long id = (Long) gridFSFile.getMetadata().get("objectId");
            String fileName = MongoHelper.cutFileName(gridFSFile.getFilename());
            String parseType = (String) gridFSFile.getMetadata().get("doctype");
            String date = (String) gridFSFile.getMetadata().get("date");
            Long parentId1 = (Long) gridFSFile.getMetadata().get("parentId");
            String createdBy = (String) gridFSFile.getMetadata().get("createdBy");

            documents.add(new Document(id1, id, parentId1, fileName, date, parseType, createdBy));
        }
        return documents;
    }

    public Document get(Long id){
        GridFSFile gridFSFile = documentDao.getById(id);
        String id1 = gridFSFile.getObjectId().toHexString();
        Long idF = (Long) gridFSFile.getMetadata().get("objectId");
        String fileName = MongoHelper.cutFileName(gridFSFile.getFilename());
        String parseType = (String) gridFSFile.getMetadata().get("doctype");
        String date = (String) gridFSFile.getMetadata().get("date");
        Long parentId1 = (Long) gridFSFile.getMetadata().get("parentId");
        String createdBy = (String) gridFSFile.getMetadata().get("createdBy");

        return new Document(id1, idF, parentId1, fileName, date, parseType, createdBy);
    }

    public GridFSDBFile getFileById(String id) {
        return documentDao.getFileById(id);
    }

    public void delete(Long id) {
        documentDao.delete(id);
    }

    public void bulkDelete(List<Long> parentIds) {
        List<Long> itemsIdToDelete = new ArrayList<Long>();
        for (Long parentId : parentIds) {
            List<Document> documentsForRemove = getAllByParentId(parentId);
            for (Document document : documentsForRemove) {
                itemsIdToDelete.add(document.getId());
            }
        }
        for (Long aLong : itemsIdToDelete) {
            delete(aLong);
        }
    }

    public boolean validateForDelete(List<Long> parentIds){
        for (Long parentId : parentIds) {
            List<Document> documentsForRemove = getAllByParentId(parentId);
            for (Document document : documentsForRemove) {
                if (!document.createdBy().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
                    return false;
                }
            }
        }
        return true;
    }


}
