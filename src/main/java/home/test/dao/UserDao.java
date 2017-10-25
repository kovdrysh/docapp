package home.test.dao;

import home.test.exceptions.UserNotFoundException;
import home.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private MongoOperations mongoOperations;

    public void save(User user){
        mongoOperations.save(user);
    }

    public void update(User user) throws UserNotFoundException {
        Update update = new Update();
        update.set("name", user.getUsername());
        update.set("surname", user.getSurname());
        update.set("email", user.getEmail());
        update.set("nickname", user.getNickname());
        update.set("password", user.getPassword());
        update.set("userRole", user.getUserRole());
        if (user.getNickname() != null || !user.getNickname().equals("")) {
            mongoOperations.updateFirst(Query.query(Criteria.where("nickname").is(user.getNickname())), update, User.class);
        } else if (user.getEmail() != null || !user.getEmail().equals("")) {
            mongoOperations.updateFirst(Query.query(Criteria.where("email").is(user.getEmail())), update, User.class);
        } else {
            throw new UserNotFoundException("Cannot update user in case fields with nickname and email are empty!");
        }
    }

}
