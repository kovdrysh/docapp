package home.test.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = Folder.COLLECTION_NAME)
public class History {
    public static final String COLLECTION_NAME = "history";

    @Id
    private Long id;
    private String action;
    private String userName;
    private Date date;
    private String message;

    public History(Long id, String action, String userName, Date date, String message) {
        this.id = id;
        this.action = action;
        this.userName = userName;
        this.date = date;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
