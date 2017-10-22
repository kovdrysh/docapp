package home.test.model;


import org.springframework.data.annotation.Id;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class Document {

    private Long id;
    private InputStream inputStream;
    private Long parentId;
    private String name;
    private String contentType;
    private String date;

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

    public Document(Long id, Long parentId, String name, String date, String type) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.date = date;
        this.type = type;
    }

    public Document(InputStream inputStream, Long parentId, String name, String contentType, String date, String type) {
        this.inputStream = inputStream;
        this.parentId = parentId;
        this.name = name;
        this.contentType = contentType;
        this.date = date;
        this.type = type;
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
}
