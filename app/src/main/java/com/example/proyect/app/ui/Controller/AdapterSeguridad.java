package com.example.proyect.app.ui.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyect.R;
import com.example.proyect.core.DataBase.models.Seguridad;

import java.util.ArrayList;

public class AdapterSeguridad extends RecyclerView.Adapter<SeguridadViewHolder> {
   private final  ArrayList<Seguridad> listCardSeg;

    private AdapterView.OnItemClickListener mListener;
    LayoutInflater inflater;
    View view;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }

    public AdapterSeguridad(Context context, ArrayList<Seguridad> list) {

        inflater = LayoutInflater.from(context);
        this.listCardSeg = list;
    }

    @Override
   public SeguridadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      view = inflater.inflate(R.layout.list_sesion, parent,false);
        return new SeguridadViewHolder(view,mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull SeguridadViewHolder holder, int position) {
        String nameCard = listCardSeg.get(position).getNameCard();
        String ip = listCardSeg.get(position).getIp();
        String quantity = listCardSeg.get(position).getQuantity();


        holder.txtnameCard.setText(nameCard);
        holder.txtIp.setText(ip);
        holder.textCantidad.setText(quantity);

    }

    @Override
    public int getItemCount() {
        return listCardSeg.size();
    }

}


  class SeguridadViewHolder extends RecyclerView.ViewHolder {
 TextView txtnameCard,txtIp,textCantidad;

    public SeguridadViewHolder(final View itemView, AdapterView.OnItemClickListener listener) {

        super(itemView);

        txtnameCard  = itemView.findViewById(R.id.textnameCard);
       txtIp  = itemView.findViewById(R.id.textIp);
        textCantidad = itemView.findViewById(R.id.textCantidad);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onItemClick(null,itemView,position,1);
                    }
                }
            }
        });
    }


 }
