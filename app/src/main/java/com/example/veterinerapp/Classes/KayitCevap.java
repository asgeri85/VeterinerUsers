
package com.example.veterinerapp.Classes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KayitCevap {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("tf")
    @Expose
    private Boolean tf;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getTf() {
        return tf;
    }

    public void setTf(Boolean tf) {
        this.tf = tf;
    }

}
