package com.example.veterinerapp.Classes;

import android.app.Activity;
import android.content.SharedPreferences;

public class GetSharedPrefences {
    private Activity activity;
    private SharedPreferences sharedPreferences;
    public GetSharedPrefences(Activity activity) {
        this.activity = activity;
    }

    public SharedPreferences getSharedPreferences() {
        sharedPreferences=activity.getApplicationContext().getSharedPreferences("oturum",0);
        return sharedPreferences;
    }

    public void setSharedPreferences(String id,String mail,String ad) {
        sharedPreferences=activity.getApplicationContext().getSharedPreferences("oturum",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("ad",ad);
        editor.putString("mail",mail);
        editor.commit();
    }

    public void delete(){
        sharedPreferences=activity.getApplicationContext().getSharedPreferences("oturum",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
