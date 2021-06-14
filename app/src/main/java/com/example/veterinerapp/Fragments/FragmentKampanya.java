package com.example.veterinerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.veterinerapp.Adapters.KampanyaAdapter;
import com.example.veterinerapp.ApiServices.ApiServis;
import com.example.veterinerapp.ApiServices.UserDao;
import com.example.veterinerapp.Classes.KampanyaSonuc;
import com.example.veterinerapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKampanya extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private UserDao dao;
    private List<KampanyaSonuc>kampanyaSonucList;
    private KampanyaAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_kampanya,container,false);
        tanimla();
        kampanyalar();
        return view;
    }

    public void tanimla(){
        recyclerView=view.findViewById(R.id.rvkampanya);
        dao= ApiServis.getDao();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        kampanyaSonucList=new ArrayList<>();
    }

    public void kampanyalar(){
        dao.kampanGetir().enqueue(new Callback<List<KampanyaSonuc>>() {
            @Override
            public void onResponse(Call<List<KampanyaSonuc>> call, Response<List<KampanyaSonuc>> response) {
                if (response.body().size()>0){
                    kampanyaSonucList=response.body();
                    adapter=new KampanyaAdapter(kampanyaSonucList,getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<KampanyaSonuc>> call, Throwable t) {
                Toast.makeText(getContext(),"Kampanya mevcut değil",Toast.LENGTH_LONG).show();
            }
        });
    }
}
