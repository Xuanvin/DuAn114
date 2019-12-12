package com.example.duana.model.remote;


import com.example.duana.Adapter.Vin.Adapter.LoaiSpResponse;
import com.example.duana.Adapter.Vin.Adapter.SanPhamResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by muase on 3/14/2018.
 */

public interface ApiService {

    @GET("get_allsanpham.php")
    Call<SanPhamResponse> getAllSanpham();

//    @GET("get_donhang_theo_makh.php")
//    Call<DonHangResponse> getDonHang();

    @GET("get_sanpham_moinhat.php")
    Call<SanPhamResponse> getSpMoinhat();

    @GET("get_loaisanpham.php")
    Call<LoaiSpResponse> getLoaiSp();

//    @GET("get_khachhang.php")
//    Call<KhachHangResponse> getAllUser();

//    @GET("check_connect.php")
//    Call<ConnectResponse> getConnect();

    @FormUrlEncoded
    @POST("get_sp_theoloai.php")
    Call<SanPhamResponse> getSpTheoloai(@Field("id_loaisp") String idLoaiSP);


//    @FormUrlEncoded
//    @POST("insert_thongtinkh.php")
//    Call<InsertKHResponse> insertThongtinkh(@Field("tenkh") String tenkh,
//                                            @Field("diachi") String diachi,
//                                            @Field("sdt") String sdt,
//                                            @Field("mail") String mail,
//                                            @Field("pass") String pass);
//
//    //update thong tin khac hang
//    @FormUrlEncoded
//    @POST("update_kh.php")
//    Call<InsertKHResponse> updateKH(@Field("makh") String makh,
//                                    @Field("diachi") String diachi,
//                                    @Field("pass") String pass);
//
//    //chitietdonhang
//    @FormUrlEncoded
//    @POST("insert_chitietdonhang.php")
//    Call<InsertChitietdonhangRespone> insertDonhang(@Field("json") String json);
}
