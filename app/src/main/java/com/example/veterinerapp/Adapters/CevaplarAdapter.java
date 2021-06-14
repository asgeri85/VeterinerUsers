package com.example.veterinerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.veterinerapp.Classes.CevapSonuc;
import com.example.veterinerapp.R;
import java.util.List;

public class CevaplarAdapter extends RecyclerView.Adapter<CevaplarAdapter.CevapTutucu>{
    private List<CevapSonuc>cevapSonucList;
    private Context mContext;

    public CevaplarAdapter(List<CevapSonuc> cevapSonucList, Context mContext) {
        this.cevapSonucList = cevapSonucList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CevapTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.cevap_card,parent,false);
        return new CevapTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CevapTutucu holder, int position) {
        CevapSonuc cevap=cevapSonucList.get(position);

        holder.textViewSoru.setText("Soru: "+cevap.getSoru());
        holder.textViewCevap.setText("Cevap:\n"+cevap.getCevap());
    }

    @Override
    public int getItemCount() {
        return cevapSonucList.size();
    }

    public class CevapTutucu extends RecyclerView.ViewHolder{
        private TextView textViewSoru,textViewCevap;
        public CevapTutucu(@NonNull View itemView) {
            super(itemView);
            textViewSoru=itemView.findViewById(R.id.textViewCardSoru);
            textViewCevap=itemView.findViewById(R.id.textViewCardCevap);
        }
    }
}
