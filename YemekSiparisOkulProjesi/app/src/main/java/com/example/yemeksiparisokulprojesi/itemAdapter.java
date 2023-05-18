package com.example.yemeksiparisokulprojesi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemeksiparisokulprojesi.model.cartItem;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {
    List<cartItem> list;

    public itemAdapter(List<cartItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(Integer.parseInt(list.get(position).resimId));
        holder.ad.setText(list.get(position).baslik);
        holder.aciklama.setText(list.get(position).aciklama);
        holder.fiyat.setText(String.valueOf(list.get(position).fiyat));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ad,aciklama,fiyat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.resim);
            ad=itemView.findViewById(R.id.ad);
            aciklama=itemView.findViewById(R.id.aciklama);
            fiyat=itemView.findViewById(R.id.fiyat);
        }
    }
}

