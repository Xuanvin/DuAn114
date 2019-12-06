package com.example.duana.model.Interface;

import com.example.duana.model.ModeDienThoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProducAPI {
    @GET("duan/{TenSP}")
    Call<List<ModeDienThoai>> search(@Path("TenSP") String keyword);

}
