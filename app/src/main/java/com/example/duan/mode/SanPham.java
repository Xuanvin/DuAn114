package com.example.duan.mode;

public class SanPham {
    private int id;
  private   String tenSP;
   private String gia;
   private String giaGiam;
   private String diaChi;
   private  int ratingbar;
   private String HinhAnh;
   private String binhluan;

    public SanPham(int id,String tenSP, String gia, String giaGiam, String diaChi, int ratingbar, String hinhAnh,String binhluan) {
        this.id=id;
        this.tenSP = tenSP;
        this.gia = gia;
        this.giaGiam = giaGiam;
        this.diaChi = diaChi;
        this.ratingbar=ratingbar;
        HinhAnh = hinhAnh;
        this.binhluan=binhluan;
    }

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }

    public int getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(int ratingbar) {
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

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
