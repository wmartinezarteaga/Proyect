package com.example.proyect.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyect.R;

import java.util.ArrayList;

import models.Sesions;

public class Adapter extends RecyclerView.Adapter<SesionViewHolder> {
    ArrayList<Sesions> listSesion;
    LayoutInflater inflater;
    View view;

    public Adapter(Context context, ArrayList<Sesions> list) {

        inflater = LayoutInflater.from(context);
        this.listSesion = list;
    }

    @Override
    public SesionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      view = inflater.inflate(R.layout.list_sesion, parent,false);
        return new SesionViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SesionViewHolder holder, int position) {
        String date = listSesion.get(position).getDate();
        String id = listSesion.get(position).getId();
        String name = listSesion.get(position).getName();
        String client = listSesion.get(position).getClient();
        String access = listSesion.get(position).getAccess();
        holder.txtid.setText(id);
        holder.txtdate.setText(date);
        holder.textname.setText(name);
        holder.textclient.setText(client);
        holder.textacces.setText(access);

    }

    @Override
    public int getItemCount() {
        return listSesion.size();
    }

}


 class  SesionViewHolder extends RecyclerView.ViewHolder{
 TextView txtdate,txtid,textname,textclient,textacces;
//jjhjh
    public SesionViewHolder(View itemView) {

        super(itemView);

       txtdate = itemView.findViewById(R.id.textDepend);
       txtid  = itemView.findViewById(R.id.textID);
       textname = itemView.findViewById(R.id.textLic);
       textclient =itemView.findViewById(R.id.textnSerie);
       textacces  =itemView.findViewById(R.id.textModel);

    }
}
