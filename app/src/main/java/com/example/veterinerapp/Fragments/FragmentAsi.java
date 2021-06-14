package com.example.veterinerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.veterinerapp.ApiServices.ApiServis;
import com.example.veterinerapp.ApiServices.UserDao;
import com.example.veterinerapp.Classes.AsiSonuc;
import com.example.veterinerapp.Classes.GetSharedPrefences;
import com.example.veterinerapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAsi extends Fragment {
    private View view;
    private CalendarPickerView calendarPickerView;
    private DateFormat dateFormat;
    private Calendar nextYear;
    private Date today;
    private List<AsiSonuc>asiSonucList;
    private List<Date>dateList;
    private UserDao dao;
    private GetSharedPrefences sharedPrefences;
    private String id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_asilar,container,false);
        tanimla();
        asiGetir(id);

        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                for (int  i=0;i<dateList.size();i++){
                    if (date.toString().equals(dateList.get(i).toString())){
                        AsiSonuc asi=asiSonucList.get(i);
                        AlertAc(asi.getPetResim(),asi.getPetAd(),asi.getAsiIsmi(),asi.getAsiTarih(),inflater);
                    }
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        return view;
    }

    public void tanimla(){
        calendarPickerView=view.findViewById(R.id.clnpicker);
        dao= ApiServis.getDao();
        sharedPrefences=new GetSharedPrefences(getActivity());
        id=sharedPrefences.getSharedPreferences().getString("id",null);
        dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        nextYear=Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);
        today=new Date();
        asiSonucList=new ArrayList<>();
        dateList=new ArrayList<>();
        calendarPickerView.init(today,nextYear.getTime());
    }

    public void asiGetir(String id){
        dao.asilarGetir(id).enqueue(new Callback<List<AsiSonuc>>() {
            @Override
            public void onResponse(Call<List<AsiSonuc>> call, Response<List<AsiSonuc>> response) {
                if (response.body().size()>0){
                    asiSonucList=response.body();
                    for (int i=0;i<asiSonucList.size();i++){
                        String dataString=response.body().get(i).getAsiTarih();
                        try {
                            Date date=dateFormat.parse(dataString);
                            dateList.add(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    calendarPickerView.init(today,nextYear.getTime()).withHighlightedDates(dateList);
                }
            }

            @Override
            public void onFailure(Call<List<AsiSonuc>> call, Throwable t) {

            }
        });
    }

    public void AlertAc(String img,String ad,String asi,String tarih,LayoutInflater inflater){
        View alert=inflater.inflate(R.layout.alert_asi,null);
        TextView textViewAd=alert.findViewById(R.id.textViewAlertAsiAd);
        TextView textViewAciklama=alert.findViewById(R.id.textViewAlertAsiAciklama);
        ImageView imageView=alert.findViewById(R.id.imageViewAlertAsi);

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setView(alert);

        builder.setCancelable(true);

        textViewAd.setText(ad);
        textViewAciklama.setText(ad+" isimli petinizin "+ tarih+" tarihinde "+asi+" aşısı yapılıcaktır.");
        Picasso.get().load(img).resize(250,250).into(imageView);
        builder.create().show();
    }
}
