package home.test.services;

import home.test.Utils.CurrentUserInfo;
import home.test.Utils.MongoHelper;
import home.test.dao.HistoryDao;
import home.test.model.Action;
import home.test.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryDao historyDao;

    public void add(Action action, String message){
        historyDao.save(new History(MongoHelper.generateId(), action.toString(), CurrentUserInfo.getName(), MongoHelper.generateCurrentDateWithTime(), message));
    }

    public List<History> getAll(){
        return historyDao.getAll();
    }

    public void remove(Long id){
        historyDao.remove(id);
    }
}
