package home.test.dao;

import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import home.test.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDao {
    @Autowired
    GridFsOperations gridOperations;

    public void save(Document document, DBObject metadata){
        gridOperations.store(document.inputStream(), document.getName(), document.getContentType(), metadata);
    }

    public GridFSFindIterable getAllbyParentId(Long parentId){
        return gridOperations.find(new Query().addCriteria(Criteria.where("metadata.parentId").is(parentId)));
     }

     public void delete(Long objectId){
        gridOperations.delete(new Query().addCriteria(Criteria.where("metadata.objectId").is(objectId)));
     }

}
