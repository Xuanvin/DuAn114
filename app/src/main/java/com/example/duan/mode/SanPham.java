package com.example.duan.mode;

public class SanPham {
    private int id;
  private   String tenSP;
   private String gia;
   private String giaGiam;
   private String diaChi;
   private  float ratingbar;
   private String HinhAnh1;
    private String HinhAnh2;
    private String HinhAnh3;
   private String binhluan;

    public SanPham(int id,String tenSP, String gia, String giaGiam, String diaChi, float ratingbar, String hinhAnh1,String binhluan,String hinhAnh2,String hinhAnh3) {
        this.id=id;
        this.tenSP = tenSP;
        this.gia = gia;
        this.giaGiam = giaGiam;
        this.diaChi = diaChi;
        this.ratingbar=ratingbar;
        HinhAnh1 = hinhAnh1;
        HinhAnh2 = hinhAnh2;
        HinhAnh3 = hinhAnh3;
        this.binhluan=binhluan;
    }

    public String getHinhAnh1() {
        return HinhAnh1;
    }

    public void setHinhAnh1(String hinhAnh1) {
        HinhAnh1 = hinhAnh1;
    }

    public String getHinhAnh2() {
        return HinhAnh2;
    }

    public void setHinhAnh2(String hinhAnh2) {
        HinhAnh2 = hinhAnh2;
    }

    public String getHinhAnh3() {
        return HinhAnh3;
    }

    public void setHinhAnh3(String hinhAnh3) {
        HinhAnh3 = hinhAnh3;
    }

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }

    public float getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(float ratingbar) {
        this.ratingbar = ratingbar;
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

    public String getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(String giaGiam) {
        this.giaGiam = giaGiam;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
