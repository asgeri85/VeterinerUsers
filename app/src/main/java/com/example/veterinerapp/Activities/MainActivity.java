package com.example.veterinerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.veterinerapp.Classes.GetSharedPrefences;
import com.example.veterinerapp.Fragments.FragmentHome;
import com.example.veterinerapp.R;

public class MainActivity extends AppCompatActivity {
    private ImageButton imageButtonHome,imageButtonCall,imageButtonExit;
    private GetSharedPrefences getSharedPrefences;
    private SharedPreferences shd;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButtonHome=findViewById(R.id.imageButtonHome);
        imageButtonCall=findViewById(R.id.imageButtonCall);
        imageButtonExit=findViewById(R.id.imageButtonExit);
        getSharedPrefences=new GetSharedPrefences(MainActivity.this);
        shd=getSharedPrefences.getSharedPreferences();
        if (shd.getString("id",null)==null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        fragment=new FragmentHome();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_home,fragment).commit();

        imageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentHome();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();

            }
        });

        imageButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPrefences.delete();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        imageButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:05321235674"));
                startActivity(intent);
            }
        });
    }
}