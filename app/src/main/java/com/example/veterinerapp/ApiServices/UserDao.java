package com.example.veterinerapp.ApiServices;

import androidx.cardview.widget.CardView;

import com.example.veterinerapp.Classes.AsiSonuc;
import com.example.veterinerapp.Classes.CevapSonuc;
import com.example.veterinerapp.Classes.GirisCevap;
import com.example.veterinerapp.Classes.KampanyaSonuc;
import com.example.veterinerapp.Classes.KayitCevap;
import com.example.veterinerapp.Classes.Petlercevap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserDao {
    @POST("kayitol.php")
    @FormUrlEncoded
    Call<KayitCevap>kayitEkle(@Field("mail") String mail, @Field("ad") String ad, @Field("sifre") String sifre);

    @POST("girisyap.php")
    @FormUrlEncoded
    Call<GirisCevap>girisYap(@Field("ad") String ad,@Field("sifre") String sifre);

    @POST("petler.php")
    @FormUrlEncoded
    Call<List<Petlercevap>>petGetir(@Field("id") String id);

    @POST("soruekle.php")
    @FormUrlEncoded
    Call<KayitCevap>soruGonder(@Field("id") String id,@Field("soru") String soru);

    @POST("cevap.php")
    @FormUrlEncoded
    Call<List<CevapSonuc>>cevapGetir(@Field("id") String id);

    @GET("kampanya.php")
    Call<List<KampanyaSonuc>>kampanGetir();

    @POST("asilar.php")
    @FormUrlEncoded
    Call<List<AsiSonuc>>asilarGetir(@Field("id") String id);

    @POST("gecmisasi.php")
    @FormUrlEncoded
    Call<List<AsiSonuc>>gecmisGetir(@Field("id") String id,@Field("pet") String pet);
}
