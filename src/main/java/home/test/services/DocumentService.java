package home.test.services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import home.test.Utils.MongoHelper;
import home.test.dao.DocumentDao;
import home.test.model.Document;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentDao documentDao;
    private static final Logger logger = Logger.getLogger(DocumentService.class);
    public void save (Long parentId, MultipartFile file){
        try{
            String type = MongoHelper.parseType(file.getOriginalFilename());
            Document document = new Document(file.getInputStream(), parentId, file.getOriginalFilename(), file.getContentType(), MongoHelper.generateCurrentDate(), type);
            DBObject metaData = new BasicDBObject();
            metaData.put("parentId", parentId);
            logger.debug("parseType: " + type);
            metaData.put("doctype", MongoHelper.parseType(file.getOriginalFilename()));
            metaData.put("date", MongoHelper.generateCurrentDate());

            documentDao.save(document, metaData);
        }catch(IOException e){
            logger.debug("Cannot create object");
        }
    }

    public List<Document> getAllByParentId(Long parentId){
        GridFSFindIterable allbyParentId = documentDao.getAllbyParentId(parentId);
        List<Document> documents = new ArrayList<Document>();
        for (GridFSFile gridFSFile : allbyParentId) {
            String fileName = gridFSFile.getFilename();
            logger.debug("fileName: " + fileName);
            String parseType = (String) gridFSFile.getMetadata().get("doctype");
            logger.debug("parseType: " + parseType);
            String date = (String) gridFSFile.getMetadata().get("date");
            logger.debug("date: " + date);
            Long parentId1 = (Long) gridFSFile.getMetadata().get("parentId");
            logger.debug("parentId: " + parentId1);
            String contentType = (String)gridFSFile.getMetadata().get("_contentType");
            logger.debug("contentType: " + contentType);

            documents.add(new Document(null, parentId1, fileName, contentType, date, parseType));
        }
        return documents;
    }


}
