
package com.example.veterinerapp.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CevapSonuc {

    @SerializedName("soruid")
    @Expose
    private String soruid;
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("cevap")
    @Expose
    private String cevap;
    @SerializedName("cevapid")
    @Expose
    private String cevapid;
    @SerializedName("soru_id")
    @Expose
    private String soruId;

    public String getSoruid() {
        return soruid;
    }

    public void setSoruid(String soruid) {
        this.soruid = soruid;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public String getCevapid() {
        return cevapid;
    }

    public void setCevapid(String cevapid) {
        this.cevapid = cevapid;
    }

    public String getSoruId() {
        return soruId;
    }

    public void setSoruId(String soruId) {
        this.soruId = soruId;
    }

}
