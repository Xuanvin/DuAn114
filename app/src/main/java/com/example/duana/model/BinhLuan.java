package com.example.duana.model;

public class BinhLuan {
    private  int id;
    private String comment;
    private String date;

    public BinhLuan(int id, String comment, String date) {
        this.id=id;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
