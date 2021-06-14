package com.example.veterinerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.veterinerapp.ApiServices.ApiServis;
import com.example.veterinerapp.ApiServices.UserDao;
import com.example.veterinerapp.Classes.GetSharedPrefences;
import com.example.veterinerapp.Classes.GirisCevap;
import com.example.veterinerapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextAd,editTextSifre;
    private Button buttonLogin,buttonKayit;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextAd=findViewById(R.id.editLoginAd);
        editTextSifre=findViewById(R.id.editLoginSifre);
        buttonLogin=findViewById(R.id.buttonLogin);
        buttonKayit=findViewById(R.id.buttonLoginKayit);
        dao= ApiServis.getDao();
        buttonKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ad=editTextAd.getText().toString();
                String sifre=editTextSifre.getText().toString();
                if (!ad.isEmpty() && !sifre.isEmpty()){
                    girisEle(ad,sifre);
                }else{
                    Toast.makeText(getApplicationContext(),"Tüm alanları doldurun",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void girisEle(String ad,String sifre){
        dao.girisYap(ad,sifre).enqueue(new Callback<GirisCevap>() {
            @Override
            public void onResponse(Call<GirisCevap> call, Response<GirisCevap> response) {
                if (response.body().getTf()){
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_SHORT).show();
                    GetSharedPrefences sharedPrefences=new GetSharedPrefences(LoginActivity.this);
                    sharedPrefences.setSharedPreferences(response.body().getId().toString(),response.body().getMail().toString(),response.body().getAd().toString());
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    editTextAd.setText("");
                    editTextSifre.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GirisCevap> call, Throwable t) {

            }
        });
    }


}