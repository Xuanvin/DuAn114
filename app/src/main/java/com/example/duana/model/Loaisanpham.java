package com.example.duana.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by muase on 4/12/2018.
 */

public class Loaisanpham {
    @SerializedName("id_loaisp")
    @Expose
    public String idLoaisp;
    @SerializedName("ten_loaisp")
    @Expose
    public String tenLoaisp;
    @SerializedName("hinhanh_loaisp")
    @Expose
    public String hinhanhLoaisp;

    public String getIdLoaisp() {
        return idLoaisp;
    }

    public String getTenLoaisp() {
        return tenLoaisp;
    }

    public String getHinhanhLoaisp() {
        return hinhanhLoaisp;
    }
}
