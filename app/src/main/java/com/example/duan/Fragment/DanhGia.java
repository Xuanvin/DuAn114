package com.example.duan.Fragment;

public class DanhGia {
String tennguoidung;
String ngayh;

    public DanhGia(String tennguoidung, String ngayh) {
        this.tennguoidung = tennguoidung;
        this.ngayh = ngayh;
    }

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getNgayh() {
        return ngayh;
    }

    public void setNgayh(String ngayh) {
        this.ngayh = ngayh;
    }
}
