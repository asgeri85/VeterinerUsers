package com.example.veterinerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinerapp.Classes.AsiSonuc;
import com.example.veterinerapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GecmisAdapter extends RecyclerView.Adapter<GecmisAdapter.GecmisTutucu>{
    private List<AsiSonuc>asiSonucList;
    private Context mContext;

    public GecmisAdapter(List<AsiSonuc> asiSonucList, Context mContext) {
        this.asiSonucList = asiSonucList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public GecmisTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.gecmis_card,parent,false);
        return new GecmisTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GecmisTutucu holder, int position) {
        AsiSonuc asi=asiSonucList.get(position);
        holder.textViewAd.setText(asi.getAsiIsmi());
        holder.textViewAciklama.setText(asi.getPetAd()+" isimli petinizin "+ asi.getAsiTarih()+" tarihinde " + asi.getAsiIsmi()+" aşısı yapılmıştır.");
        Picasso.get().load(asi.getPetResim()).resize(250,250).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return asiSonucList.size();
    }

    public class GecmisTutucu extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textViewAd,textViewAciklama;
        public GecmisTutucu(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewGecmisCard);
            textViewAd=itemView.findViewById(R.id.textViewGecmisBaslik);
            textViewAciklama=itemView.findViewById(R.id.textGecmisAciklama);
        }
    }
}
