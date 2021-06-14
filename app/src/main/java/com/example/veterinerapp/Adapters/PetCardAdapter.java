package com.example.veterinerapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.veterinerapp.Classes.Petlercevap;
import com.example.veterinerapp.Fragments.FragmentGecmis;
import com.example.veterinerapp.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PetCardAdapter extends RecyclerView.Adapter<PetCardAdapter.PetTutucu> {
    private List<Petlercevap>petlercevapList;
    private Context mContext;
    private String id;
    private Fragment fragment;
    public PetCardAdapter(List<Petlercevap> petlercevapList, Context mContext,String id) {
        this.petlercevapList = petlercevapList;
        this.mContext = mContext;
        this.id=id;
    }

    @NonNull
    @Override
    public PetTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.pet_card,parent,false);
        return new PetTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetTutucu holder, int position) {
        Petlercevap pet=petlercevapList.get(position);

        holder.textViewAd.setText(pet.getPetAd());
        holder.textViewAciklama.setText(pet.getPetCins());
        Picasso.get().load(pet.getPetResim()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentGecmis();
                Bundle bundle=new Bundle();
                bundle.putString("pet",pet.getId());
                fragment.setArguments(bundle);
                ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return petlercevapList.size();
    }

    public class PetTutucu extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;
        private TextView textViewAd,textViewAciklama;
        public PetTutucu(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardRvPet);
            imageView=itemView.findViewById(R.id.imagePetcard);
            textViewAd=itemView.findViewById(R.id.textViewPetCardAd);
            textViewAciklama=itemView.findViewById(R.id.textViewPetCardAciklama);
        }
    }
}
