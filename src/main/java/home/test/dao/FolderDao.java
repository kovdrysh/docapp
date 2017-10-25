package home.test.dao;

import home.test.model.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Repository
public class FolderDao {
    @Autowired
    private MongoOperations mongoOperations;

    public void save(Folder folder){
        mongoOperations.save(folder);
    }

    public void update(Folder folder){
        Update update = new Update();
        update.set("name", folder.getName());
        mongoOperations.updateFirst(Query.query(Criteria.where("id").is(folder.getId())), update, Folder.class);
    }

    public Folder get(Long id){
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), Folder.class);
    }

    public List<Folder> getAll(){
        return mongoOperations.findAll(Folder.class);
    }

    public List<Folder> getAllByParentId(Long parentId){
        return mongoOperations.find(Query.query(Criteria.where("parentId").is(parentId)), Folder.class);
    }

    public void remove(Long id){
        mongoOperations.remove(Query.query(Criteria.where("id").is(id)), Folder.class);
    }

    public void bulkRemove(List<Long> ids){
        for (Long id : ids) {
            remove(id);
        }
    }
}
