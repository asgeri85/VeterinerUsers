package com.example.veterinerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.veterinerapp.ApiServices.ApiServis;
import com.example.veterinerapp.Classes.KayitCevap;
import com.example.veterinerapp.ApiServices.UserDao;
import com.example.veterinerapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextAd,editTextMail,editTextSİfre;
    private Button button;
    private UserDao dao;
    private Boolean mailKonytrol=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextAd=findViewById(R.id.editUsarname);
        editTextMail=findViewById(R.id.editMail);
        editTextSİfre=findViewById(R.id.editSifre);
        button=findViewById(R.id.buttonkayit);
        dao= ApiServis.getDao();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String mail=editTextMail.getText().toString();
               isValidEmail(mail);
               String ad=editTextAd.getText().toString();
               String sifre=editTextSİfre.getText().toString();
               if (mailKonytrol && !ad.isEmpty() && !sifre.isEmpty()){
                   kayitEle(ad,mail,sifre);
               }else{
                   Toast.makeText(getApplicationContext(),"Geçerli bir mail girin veya tüm alanları doldurun",Toast.LENGTH_LONG).show();
               }
            }
        });

    }

    public void kayitEle(String ad,String mail,String sifre){
        dao.kayitEkle(mail,ad,sifre).enqueue(new Callback<KayitCevap>() {
            @Override
            public void onResponse(Call<KayitCevap> call, Response<KayitCevap> response) {
                if (response.body().getTf()){
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_SHORT).show();
                    editTextAd.setText("");
                    editTextMail.setText("");
                    editTextSİfre.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KayitCevap> call, Throwable t) {

            }
        });
    }


    public boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            mailKonytrol= android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
            return mailKonytrol;
        }
    }
}