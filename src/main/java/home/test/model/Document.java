package home.test.model;

import java.io.InputStream;

public class Document {

    private String _id;
    private Long id;
    private InputStream inputStream;
    private Long parentId;
    private String name;
    private String contentType;
    private String date;
    private String createdBy;
    private String deletable;

    public String createdBy(){
        return createdBy;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public Document(InputStream inputStream, Long parentId) {
        this.inputStream = inputStream;
        this.parentId = parentId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBSONIdAsText(){
        return this._id.toString();
    }



    public Document(String _id, Long id, Long parentId, String name, String date, String type, String createdBy, String contentType) {
        this._id = _id;
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.date = date;
        this.type = type;
        this.createdBy = createdBy;
        this.contentType = contentType;
    }

    public Document(InputStream inputStream, Long parentId, String name, String contentType, String date, String type) {
        this.inputStream = inputStream;
        this.parentId = parentId;
        this.name = name;
        this.contentType = contentType;
        this.date = date;
        this.type = type;
    }

    public Document(String name, InputStream inputStream, String contentType){
        this.name = name;
        this.inputStream = inputStream;
        this.contentType = contentType;
    }

    public InputStream inputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getDeletable() {
        return deletable;
    }

    public void setDeletable(String deletable) {
        this.deletable = deletable;
    }
}
