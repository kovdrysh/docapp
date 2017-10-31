package home.test.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Folder.COLLECTION_NAME)
public class Folder {
    public static final String COLLECTION_NAME = "folders";

    @Id
    private Long id;
    private Long parentId;
    private String createdBy;
    private String name;
    private String date;
    private String editable;
    private String deletable;

    public Folder(){}

    public Folder(Long id, String name) {
        this.id = id;
    }

    public Folder(Long id, Long parentId, String name, String date) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.date = date;
    }

    public Folder(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getEditable(){ return editable; }

    public String returnEdit(){return editable;}

    public void setEditable(String editable){ this.editable = editable; }

    public String returnDelete(){return deletable; }

    public String getDeletable(){ return deletable; }

    public void setDeletable(String deletable){ this.deletable = deletable; }
}
