package home.test.dao;


import home.test.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryDao {
    @Autowired
    private MongoOperations mongoOperations;

    public void save(History history){
        mongoOperations.save(history);
    }

    public List<History> getAll(){
        return mongoOperations.findAll(History.class);
    }

    public void remove(Long id){
        mongoOperations.remove(Query.query(Criteria.where("id").is(id)), History.class);
    }
}
