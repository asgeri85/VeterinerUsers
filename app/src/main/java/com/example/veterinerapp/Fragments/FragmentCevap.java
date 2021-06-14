package com.example.veterinerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinerapp.Adapters.CevaplarAdapter;
import com.example.veterinerapp.ApiServices.ApiServis;
import com.example.veterinerapp.ApiServices.UserDao;
import com.example.veterinerapp.Classes.CevapSonuc;
import com.example.veterinerapp.Classes.GetSharedPrefences;
import com.example.veterinerapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCevap extends Fragment {
    private View view;
    private RecyclerView rv;
    private UserDao dao;
    private List<CevapSonuc>cevapSonucList;
    private CevaplarAdapter adapter;
    private GetSharedPrefences sharedPrefences;
    private String id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_cevap,container,false);
        tanimla();
        cevaplar(id);
        return view;
    }

    public void tanimla(){
        rv=view.findViewById(R.id.rvCevap);
        dao= ApiServis.getDao();
        sharedPrefences=new GetSharedPrefences(getActivity());
        id=sharedPrefences.getSharedPreferences().getString("id",null);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        cevapSonucList=new ArrayList<>();
    }

    public void cevaplar(String id){
        dao.cevapGetir(id).enqueue(new Callback<List<CevapSonuc>>() {
            @Override
            public void onResponse(Call<List<CevapSonuc>> call, Response<List<CevapSonuc>> response) {
                if (response.body().size()>0){
                    cevapSonucList=response.body();
                    adapter=new CevaplarAdapter(cevapSonucList,getContext());
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CevapSonuc>> call, Throwable t) {
                Toast.makeText(getContext(),"Sorduğunuz soru yok",Toast.LENGTH_LONG).show();
            }
        });
    }
}
