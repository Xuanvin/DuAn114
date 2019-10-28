package com.example.duana.mode;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public class DataFish implements Serializable {
    @SerializedName("TenSP")
    public String tensp;
    @SerializedName("Gia")
    public String gia;
    @SerializedName("GiamGia")
    public String giamgia;
    @SerializedName("ID")
    public int id;

}
