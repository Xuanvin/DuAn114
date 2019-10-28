package com.example.duana.mode;

public class ModeHangCongNghe {
    private int id;
    private String img1;
    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String danhgia1;

    public ModeHangCongNghe(int id,  String txt1, String txt2, String txt3, String txt4, String danhgia1,String img1) {
        this.id = id;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.txt3 = txt3;
        this.txt4 = txt4;
        this.danhgia1 = danhgia1;
        this.img1 = img1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public String getTxt3() {
        return txt3;
    }

    public void setTxt3(String txt3) {
        this.txt3 = txt3;
    }

    public String getTxt4() {
        return txt4;
    }

    public void setTxt4(String txt4) {
        this.txt4 = txt4;
    }

    public String getDanhgia1() {
        return danhgia1;
    }

    public void setDanhgia1(String danhgia1) {
        this.danhgia1 = danhgia1;
    }
}
