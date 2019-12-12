package com.example.duana.Adapter.Vin.Adapter;

import com.example.duana.model.Loaisanpham;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muase on 4/12/2018.
 */

public class LoaiSpResponse {
    @SerializedName("loaisanpham")
    @Expose
    public List<Loaisanpham> loaisanpham = null;
    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;

    public List<Loaisanpham> getLoaisanpham() {
        return loaisanpham;
    }

    public Integer getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
