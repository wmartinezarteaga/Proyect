package com.example.proyect.app.ui.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyect.R;
import com.example.proyect.core.DataBase.models.Logs;

import java.util.ArrayList;

public class AdapterGeneralInformation extends RecyclerView.Adapter<LogsViewHolder> {
    ArrayList<Logs> listCardevent;
    LayoutInflater inflater;
    View view;

    public AdapterGeneralInformation(Context context, ArrayList<Logs> list) {

        inflater = LayoutInflater.from(context);
        this.listCardevent = list;
    }


    @NonNull
    @Override
    public LogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.list_logs, parent,false);
        return new LogsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsViewHolder holder, int position) {
        String ip = listCardevent.get(position).getIp();
       String date = listCardevent.get(position).getDate();
       String serial = listCardevent.get(position).getSerial();
        String response = listCardevent.get(position).getResponse();
        String number = listCardevent.get(position).getNumCard();
       holder.textDate.setText(date);
       holder.txtIp.setText(ip);
       holder.textSerial.setText(serial);
        holder.txtresposne.setText(response);
        holder.textNumber.setText(number);

    }

    @Override
    public int getItemCount() {return listCardevent.toArray().length;}
}


class LogsViewHolder extends RecyclerView.ViewHolder {
    TextView txtresposne, txtIp, textDate, textSerial, textNumber;
//m nbvmnb
    public LogsViewHolder(View itemView) {

        super(itemView);

     txtresposne = itemView.findViewById(R.id.textresponlog);
     txtIp = itemView.findViewById(R.id.textiplog);
     textDate = itemView.findViewById(R.id.txtfechalog);
         textSerial = itemView.findViewById(R.id.txtseriallog);
        textNumber = itemView.findViewById(R.id.textnumlog);
    }
}