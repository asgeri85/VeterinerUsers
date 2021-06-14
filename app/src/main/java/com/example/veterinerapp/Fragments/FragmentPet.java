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
import com.example.veterinerapp.Adapters.PetCardAdapter;
import com.example.veterinerapp.ApiServices.ApiServis;
import com.example.veterinerapp.ApiServices.UserDao;
import com.example.veterinerapp.Classes.GetSharedPrefences;
import com.example.veterinerapp.Classes.Petlercevap;
import com.example.veterinerapp.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPet extends Fragment {
   private View view;
   private RecyclerView rv;
   private UserDao dao;
   private List<Petlercevap>petlercevapLisr;
   private PetCardAdapter adapter;
   private GetSharedPrefences sharedPrefences;
   private String id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_petler,container,false);
        tanimla();
        petgetir(id);
        return view;
    }

    public void tanimla(){
        rv=view.findViewById(R.id.rvPet);
        dao= ApiServis.getDao();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        petlercevapLisr=new ArrayList<>();
        sharedPrefences=new GetSharedPrefences(getActivity());
        id=sharedPrefences.getSharedPreferences().getString("id",null);
    }

    public void petgetir(String id){
        dao.petGetir(id).enqueue(new Callback<List<Petlercevap>>() {
            @Override
            public void onResponse(Call<List<Petlercevap>> call, Response<List<Petlercevap>> response) {
                if (!response.body().isEmpty()){
                    petlercevapLisr=response.body();
                    adapter=new PetCardAdapter(petlercevapLisr,getContext(),id);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Petlercevap>> call, Throwable t) {
                Toast.makeText(getContext(),"Petiniz yok",Toast.LENGTH_LONG).show();
            }
        });
    }
}
