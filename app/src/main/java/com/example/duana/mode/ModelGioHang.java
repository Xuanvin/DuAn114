package com.example.duana.mode;

public class ModelGioHang {
    private int id;
    private String TenSP;
    private String GiaSP;
    private String GiamgiaSP;
    private String Img;

    public ModelGioHang(int id, String tenSP, String giaSP,String GiamgiaSP,String Img) {
       this.id = id;
        this.TenSP = tenSP;
        this.GiaSP = giaSP;
        this.GiamgiaSP=GiamgiaSP;
        this.Img=Img;
    }

    public String getGiamgiaSP() {
        return GiamgiaSP;
    }

    public void setGiamgiaSP(String giamgiaSP) {
        GiamgiaSP = giamgiaSP;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(String giaSP) {
        GiaSP = giaSP;
    }
}
