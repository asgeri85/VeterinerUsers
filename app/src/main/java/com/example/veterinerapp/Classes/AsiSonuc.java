
package com.example.veterinerapp.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AsiSonuc {

    @SerializedName("pet_ad")
    @Expose
    private String petAd;
    @SerializedName("pet_tur")
    @Expose
    private String petTur;
    @SerializedName("pet_cins")
    @Expose
    private String petCins;
    @SerializedName("pet_resim")
    @Expose
    private String petResim;
    @SerializedName("asi_tarih")
    @Expose
    private String asiTarih;
    @SerializedName("asi_ismi")
    @Expose
    private String asiIsmi;

    public String getPetAd() {
        return petAd;
    }

    public void setPetAd(String petAd) {
        this.petAd = petAd;
    }

    public String getPetTur() {
        return petTur;
    }

    public void setPetTur(String petTur) {
        this.petTur = petTur;
    }

    public String getPetCins() {
        return petCins;
    }

    public void setPetCins(String petCins) {
        this.petCins = petCins;
    }

    public String getPetResim() {
        return petResim;
    }

    public void setPetResim(String petResim) {
        this.petResim = petResim;
    }

    public String getAsiTarih() {
        return asiTarih;
    }

    public void setAsiTarih(String asiTarih) {
        this.asiTarih = asiTarih;
    }

    public String getAsiIsmi() {
        return asiIsmi;
    }

    public void setAsiIsmi(String asiIsmi) {
        this.asiIsmi = asiIsmi;
    }

}
