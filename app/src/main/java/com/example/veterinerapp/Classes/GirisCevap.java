
package com.example.veterinerapp.Classes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GirisCevap {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("ad")
    @Expose
    private Object ad;
    @SerializedName("sifre")
    @Expose
    private Object sifre;
    @SerializedName("durum")
    @Expose
    private String durum;
    @SerializedName("mail")
    @Expose
    private Object mail;
    @SerializedName("tf")
    @Expose
    private Boolean tf;
    @SerializedName("text")
    @Expose
    private String text;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
    public Object getAd() {
        return ad;
    }

    public void setAd(Object ad) {
        this.ad = ad;
    }

    public Object getSifre() {
        return sifre;
    }

    public void setSifre(Object sifre) {
        this.sifre = sifre;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public Object getMail() {
        return mail;
    }

    public void setMail(Object mail) {
        this.mail = mail;
    }

    public Boolean getTf() {
        return tf;
    }

    public void setTf(Boolean tf) {
        this.tf = tf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
