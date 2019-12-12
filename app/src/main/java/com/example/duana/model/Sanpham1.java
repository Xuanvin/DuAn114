package com.example.duana.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by muase on 4/12/2018.
 */

public class Sanpham1 implements Serializable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("id_category")
    @Expose
    public  int id_category;
    @SerializedName("data")
    @Expose
    public  String data;
    @SerializedName("tensp")
    @Expose
    public String tensp;
    @SerializedName("giasp")
    @Expose
    public String giasp;
    @SerializedName("hinhanhsp")
    @Expose
    public String hinhanhsp;
    @SerializedName("hinhanhsp1")
    @Expose
    public String hinhanhsp1;
    @SerializedName("hinhanhsp2")
    @Expose
    public String hinhanhsp2;
    @SerializedName("motasp")
    @Expose
    public String motasp;
    @SerializedName("motasp1")
    @Expose
    public String motasp1;
    @SerializedName("motasp2")
    @Expose
    public String motasp2;
    @SerializedName("id_loaisp")
    @Expose
    public String idLoaisp;

    public String getHinhanhsp1() {
        return hinhanhsp1;
    }


    public String getHinhanhsp2() {
        return hinhanhsp2;
    }


    public String getMotasp1() {
        return motasp1;
    }

    public void setMotasp1(String motasp1) {
        this.motasp1 = motasp1;
    }

    public String getMotasp2() {
        return motasp2;
    }

    public void setMotasp2(String motasp2) {
        this.motasp2 = motasp2;
    }

    public String getId() {
        return id;
    }
    public int getId_category() {
        return id_category;
    }

    public String getData() {
        return data;
    }

    public String getTensp() {
        return tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public String getMotasp() {
        return motasp;
    }

    public String getIdLoaisp() {
        return idLoaisp;
    }
}
