package com.example.duana.model;

public class MuaSP {
    private int id;
    private   String tenSP;
    private String gia;

    public MuaSP(int id, String tenSP, String gia) {
        this.id = id;
        this.tenSP = tenSP;
        this.gia = gia;
    }

    public MuaSP() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
