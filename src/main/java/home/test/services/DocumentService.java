package home.test.services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import home.test.Utils.CurrentUserInfo;
import home.test.Utils.EmailSender;
import home.test.Utils.MongoHelper;
import home.test.dao.DocumentDao;
import home.test.exceptions.UserNotFoundException;
import home.test.model.Action;
import home.test.model.Document;
import home.test.model.User;
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
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserService userService;

    private static final String TEXT_MESSAGE = "Your document was deleted. Doc name: ";
    private static final String SUBJECT = "An item was deleted!";
    private static final String FROM = "docappsupport@gmail.com";

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
            historyService.add(Action.Create, "A new document was added. Doc name: " + document.getName());
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
            String isDelete = checkForDelete(createdBy).toString();
            String contentType = (String) gridFSFile.getMetadata().get("_contentType");
            Document doc = new Document(id1, id, parentId1, fileName, date, parseType, createdBy, contentType);
            doc.setDeletable(isDelete);
            documents.add(doc);

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
        String contentType = (String) gridFSFile.getMetadata().get("_contentType");

        return new Document(id1, idF, parentId1, fileName, date, parseType, createdBy, contentType);
    }

    public GridFSDBFile getFileById(String id) {
        return documentDao.getFileById(id);
    }

    public void delete(Long id) {
        Document document = get(id);
        documentDao.delete(id);
        sendEmail(document);
        historyService.add(Action.Delete, "A document was deleted. Doc name: " + document.getName());
    }

    public void bulkDelete(List<Long> parentIds) {
        List<Long> itemsIdToDelete = new ArrayList<Long>();
        for (Long parentId : parentIds) {
            List<Document> documentsForRemove = getAllByParentId(parentId);
            for (Document document : documentsForRemove) {
                itemsIdToDelete.add(document.getId());
            }
        }
        String itemNameToDelete = "";
        for (Long aLong : itemsIdToDelete) {
            itemNameToDelete = get(aLong).getName();
            delete(aLong);
            historyService.add(Action.Delete, "A document was deleted. Document name: " + itemNameToDelete);
        }
    }

    private Boolean checkForDelete(String createdBy){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("Moderator")){
            return true;
        }
        else if (createdBy.equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            return true;
        }
        return false;
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

    private void sendEmail(Document document) {
        if (!CurrentUserInfo.isCurrentUser(document.createdBy())) {
            User user = userService.getUser(document.createdBy());
            if (!user.getEmail().equals("") || user.getEmail() != null) {
                new EmailSender().send(SUBJECT, TEXT_MESSAGE + document.getName(), FROM, user.getEmail());
            }
        }
    }
}
