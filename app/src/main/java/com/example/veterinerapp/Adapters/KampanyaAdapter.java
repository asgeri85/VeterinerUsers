package com.example.veterinerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinerapp.Classes.KampanyaSonuc;
import com.example.veterinerapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KampanyaAdapter extends RecyclerView.Adapter<KampanyaAdapter.KampanyaTutucu> {
    private List<KampanyaSonuc>kampanyaSonucList;
    private Context mContext;

    public KampanyaAdapter(List<KampanyaSonuc> kampanyaSonucList, Context mContext) {
        this.kampanyaSonucList = kampanyaSonucList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public KampanyaTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.kampanya_card,parent,false);
        return new KampanyaTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KampanyaTutucu holder, int position) {
        KampanyaSonuc kampanya=kampanyaSonucList.get(position);
        holder.textViewAd.setText(kampanya.getBaslik());
        holder.textViewAciklama.setText(kampanya.getAciklama());
        Picasso.get().load(kampanya.getResim()).resize(300,300).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return kampanyaSonucList.size();
    }

    public class KampanyaTutucu extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textViewAd,textViewAciklama;
        public KampanyaTutucu(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewKampanya);
            textViewAd=itemView.findViewById(R.id.textkampanyaBaslik);
            textViewAciklama=itemView.findViewById(R.id.textViewKampanyaAciklama);
        }
    }
}
