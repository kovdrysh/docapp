package home.test.services;

import home.test.dao.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServices {
    @Autowired
    private HistoryDao historyDao;

    public void add(String action, String message){

    }
}
