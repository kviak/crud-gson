package ru.kviak.gson.model;

import ru.kviak.gson.util.Status;

import java.util.Date;
import java.util.List;

public class Post {
    private int id;
    private String content;
    private Date created;
    private Date updated;
    private List<Label> labels;
    private Status status;

    public Post(int id, String content, List<Label> labels, Status status) {
        this.id = id;
        this.content = content;
        this.labels = labels;
        this.status = status;
        this.created = new Date();
        this.updated = new Date();
        System.out.println(created);
        System.out.println(updated);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", labels=" + labels +
                ", status=" + status +
                '}';
    }
}
