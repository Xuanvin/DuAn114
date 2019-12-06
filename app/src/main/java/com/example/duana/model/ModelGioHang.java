package com.example.duana.model;

public class ModelGioHang {
    private  int id_detell;
    private int id;
    private int tongso;
    private String TenSP;
    private int GiaSP;
    private String GiamgiaSP;
    private String Img;


    public ModelGioHang(int id_detell,int id ,int tongso, String tenSP, int giaSP,String GiamgiaSP,String Img) {
        this.id_detell=id_detell;
       this.id = id;
       this.tongso=tongso;
        this.TenSP = tenSP;
        this.GiaSP = giaSP;
        this.GiamgiaSP=GiamgiaSP;
        this.Img=Img;
    }

    public int getTongso() {
        return tongso;
    }

    public void setTongso(int tongso) {
        this.tongso = tongso;
    }

    public int getId_detell() {
        return id_detell;
    }

    public void setId_detell(int id_detell) {
        this.id_detell = id_detell;
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

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    @Override
    public String toString() {
        return "ModelGioHang{" +
                "id_detell=" + id_detell +
                ", id=" + id +
                ", tongso=" + tongso +
                ", TenSP='" + TenSP + '\'' +
                ", GiaSP=" + GiaSP +
                ", GiamgiaSP='" + GiamgiaSP + '\'' +
                ", Img='" + Img + '\'' +
                '}';
    }
}
