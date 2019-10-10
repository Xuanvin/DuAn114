package com.example.duana.mode;

public class Review {
    private  int png;
    private  String txt;

    public Review(int png, String txt) {
        this.png = png;
        this.txt = txt;
    }

    public int getPng() {
        return png;
    }

    public void setPng(int png) {
        this.png = png;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
