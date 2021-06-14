package com.example.veterinerapp.ApiServices;

public class ApiServis {
    public static final String URL="http://microwebservice.net/ecodation/veteriner/";
    public static UserDao getDao(){
        return Retrofitclient.getRetrofit(URL).create(UserDao.class);
    }
}
