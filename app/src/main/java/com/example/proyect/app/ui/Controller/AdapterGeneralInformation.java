package com.example.proyect.app.ui.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyect.R;
import com.example.proyect.core.DataBase.models.Eventos;

import java.util.ArrayList;

public class AdapterGeneralInformation extends RecyclerView.Adapter<EventosViewHolder> {
    ArrayList<Eventos> listCardevent;
    LayoutInflater inflater;
    View view;

    public AdapterGeneralInformation(Context context, ArrayList<Eventos> list) {

        inflater = LayoutInflater.from(context);
        this.listCardevent = list;
    }


    @NonNull
    @Override
    public EventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EventosViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class EventosViewHolder extends RecyclerView.ViewHolder {
    TextView txtnameCard, txtIp, textCantidad;

    public EventosViewHolder(View itemView) {

        super(itemView);

        txtnameCard = itemView.findViewById(R.id.textnameCard);
        txtIp = itemView.findViewById(R.id.textIp);
        textCantidad = itemView.findViewById(R.id.textCantidad);


    }
}