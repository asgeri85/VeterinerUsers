package com.example.veterinerapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.veterinerapp.ApiServices.ApiServis;
import com.example.veterinerapp.ApiServices.UserDao;
import com.example.veterinerapp.Classes.GetSharedPrefences;
import com.example.veterinerapp.Classes.KayitCevap;
import com.example.veterinerapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    private View view;
    private CardView cardAsi,cardSoru,cardCevap,cardSanal,cardKampanya,cardIletisim;
    private Fragment fragment;
    private UserDao dao;
    private String id;
    private GetSharedPrefences sharedPrefences;
    private  static EditText editext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        tanimla();

        cardSanal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentPet();
                getFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });

        cardSoru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertAc(inflater);
            }
        });

        cardCevap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentCevap();
                getFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });

        cardKampanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentKampanya();
                getFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });

        cardAsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentAsi();
                getFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });
        return view;
    }

    public void tanimla(){
        cardCevap=view.findViewById(R.id.cardCevap);
        cardAsi=view.findViewById(R.id.cardAsiTakib);
        cardSoru=view.findViewById(R.id.cardViewSoru);
        cardSanal=view.findViewById(R.id.cardViewSanalkart);
        cardIletisim=view.findViewById(R.id.cardIletisim);
        cardKampanya=view.findViewById(R.id.cardkampanya);
        dao= ApiServis.getDao();
        sharedPrefences=new GetSharedPrefences(getActivity());
        id=sharedPrefences.getSharedPreferences().getString("id",null);
    }

    public void alertAc(LayoutInflater inflater){
        View tasarim=inflater.inflate(R.layout.alert_card,null);
        editext=tasarim.findViewById(R.id.editAlert);

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setView(tasarim);
        builder.setPositiveButton("Soru Sor", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String soru=editext.getText().toString();
                if (!soru.isEmpty()){
                    soruSor(id,soru);
                }else {
                    Toast.makeText(getContext(),"Soru boş olamaz",Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setCancelable(true);
        builder.create().show();
    }

    public void soruSor(String id,String soru){
        dao.soruGonder(id,soru).enqueue(new Callback<KayitCevap>() {
            @Override
            public void onResponse(Call<KayitCevap> call, Response<KayitCevap> response) {
                if (response.body().getTf()){
                    Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KayitCevap> call, Throwable t) {

            }
        });
    }
}
