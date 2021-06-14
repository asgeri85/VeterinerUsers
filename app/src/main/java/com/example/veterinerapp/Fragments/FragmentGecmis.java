package com.example.veterinerapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.veterinerapp.Adapters.GecmisAdapter;
import com.example.veterinerapp.ApiServices.ApiServis;
import com.example.veterinerapp.ApiServices.UserDao;
import com.example.veterinerapp.Classes.AsiSonuc;
import com.example.veterinerapp.Classes.GetSharedPrefences;
import com.example.veterinerapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGecmis extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private String petİd,id;
    private GetSharedPrefences sharedPrefences;
    private UserDao dao;
    private List<AsiSonuc>asiSonucList;
    private GecmisAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_gecmis,container,false);
        tanimla();
        gecmis(id,petİd);
        return view;
    }

    public void tanimla(){
        recyclerView=view.findViewById(R.id.rvGecmis);
        dao= ApiServis.getDao();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        petİd = getArguments().getString("pet");
        sharedPrefences=new GetSharedPrefences(getActivity());
        id=sharedPrefences.getSharedPreferences().getString("id",null);
        asiSonucList=new ArrayList<>();
    }

    public void gecmis(String id,String pet){
        dao.gecmisGetir(id,pet).enqueue(new Callback<List<AsiSonuc>>() {
            @Override
            public void onResponse(Call<List<AsiSonuc>> call, Response<List<AsiSonuc>> response) {
                if (response.body().size()>0){
                    asiSonucList=response.body();
                    adapter=new GecmisAdapter(asiSonucList,getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<AsiSonuc>> call, Throwable t) {
                Toast.makeText(getContext(),"Prtiniz bulunmamaktadır",Toast.LENGTH_LONG).show();
            }
        });
    }
}
