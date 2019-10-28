package com.example.duana.mode;

public class ModelLaptapdell {
    private int id;
    private String img;
    private String tenSp;
    private  String giasp;
    private String giamgiasp;
    private String khuyenmaisp;

    public ModelLaptapdell(int id, String img, String tenSp, String giasp, String giamgiasp, String khuyenmaisp) {
        this.id = id;
        this.img = img;
        this.tenSp = tenSp;
        this.giasp = giasp;
        this.giamgiasp = giamgiasp;
        this.khuyenmaisp = khuyenmaisp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public String getGiamgiasp() {
        return giamgiasp;
    }

    public void setGiamgiasp(String giamgiasp) {
        this.giamgiasp = giamgiasp;
    }

    public String getKhuyenmaisp() {
        return khuyenmaisp;
    }

    public void setKhuyenmaisp(String khuyenmaisp) {
        this.khuyenmaisp = khuyenmaisp;
    }
}
