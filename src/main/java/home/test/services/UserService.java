package home.test.services;


import home.test.dao.UserDao;
import home.test.exceptions.UserNotFoundException;
import home.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAll(){
        return userDao.getAll();
    }

    public void add(User user){
        userDao.save(user);
    }

    public void update(User user){
        try {
            userDao.update(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id){
        userDao.delete(id);
    }
}
