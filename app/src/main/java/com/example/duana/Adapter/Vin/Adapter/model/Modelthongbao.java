package com.example.duana.Adapter.Vin.Adapter.model;

public class Modelthongbao {

    private int id;
    private String name;
    private String ngay;
    private  String thongtin;
    private  String id_capnhattin;
    private  String img1;


    public Modelthongbao(int id, String name, String ngay, String thongtin, String id_capnhattin, String img1) {
        this.img1=img1;
        this.id = id;
        this.name = name;
        this.ngay = ngay;
        this.thongtin = thongtin;
        this.id_capnhattin = id_capnhattin;

    }
    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getId_capnhattin() {
        return id_capnhattin;
    }

    public void setId_capnhattin(String id_capnhattin) {
        this.id_capnhattin = id_capnhattin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }
}

