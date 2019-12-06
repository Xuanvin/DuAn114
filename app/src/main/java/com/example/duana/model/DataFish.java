package com.example.duana.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
