package home.test.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import home.test.model.Document;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDao {
    @Autowired
    GridFsOperations gridOperations;
    @Autowired
    GridFsTemplate template;

    public void save(Document document, DBObject metadata){
        gridOperations.store(document.inputStream(), document.getName(), document.getContentType(), metadata);
    }

    public GridFSFindIterable getAllbyParentId(Long parentId){
        return gridOperations.find(new Query().addCriteria(Criteria.where("metadata.parentId").is(parentId)));
     }

    public GridFSFile getById(Long id){
        return gridOperations.findOne(new Query().addCriteria(Criteria.where("_id").is(id)));
    }

    public GridFSDBFile getFileById(String id){
         BasicDBObject query = new BasicDBObject();
         query.put("_id", new ObjectId(id));

         MongoClient mongoClient = new MongoClient("localhost", 27017);
         DB mongoDB = mongoClient.getDB("mydb");
         GridFS fileStore = new GridFS(mongoDB);
         return fileStore.findOne(query);
    }


     public void delete(Long objectId){
        gridOperations.delete(new Query().addCriteria(Criteria.where("metadata.objectId").is(objectId)));
     }

}
