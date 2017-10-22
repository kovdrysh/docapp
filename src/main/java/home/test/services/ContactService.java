package home.test.services;

import home.test.Utils.MongoHelper;
import home.test.dao.ContactDao;
import home.test.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;

    public void add(Contact contact){
        contact.setId(MongoHelper.generateId());
        contactDao.save(contact);
    }

    public void update(Contact contact){
        contactDao.save(contact);
    }

    public Contact get(Long id){
        return contactDao.get(id);
    }

    public List<Contact> getAll(){
        return contactDao.getAll();
    }

    public void remove (Long id){
        contactDao.remove(id);
    }


}
