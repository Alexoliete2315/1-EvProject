package com.example.proy1bueno.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proy1bueno.R;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.productFile.view.ProductFile;

import java.util.ArrayList;

public class ProductRatedAdapter extends RecyclerView.Adapter<ProductRatedAdapter.ProductRatedViewHolder>{

    ArrayList<Valoracion> lstValoracion;
    Context context;
    public ProductRatedAdapter(ArrayList<Valoracion> lstValoracion) {
        this.lstValoracion = lstValoracion;
    }

    @NonNull
    @Override
    public ProductRatedAdapter.ProductRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_rated,parent,false);
        return new ProductRatedAdapter.ProductRatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRatedAdapter.ProductRatedViewHolder holder, int position) {
            holder.nombreProductAdapter.setText(lstValoracion.get(position).getNombreProducto());
            holder.valoracionMediaAdapter.setText("Puntuación media: " + lstValoracion.get(position).getPromedioValoracion());
            String imageUrl = "http://192.168.104.76:8080/untitled/img/"+lstValoracion.get(position).getImagenProducto();
            Glide.with(context).load(imageUrl).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            Valoracion valoracion = lstValoracion.get(position);
            int idProduct = valoracion.getIdProduct();
            Intent intent = new Intent(holder.itemView.getContext(), ProductFile.class);
            intent.putExtra("idProduct", idProduct);
            context.startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
       return lstValoracion.size();
    }

    public class ProductRatedViewHolder extends RecyclerView.ViewHolder{
        TextView nombreProductAdapter;
        TextView valoracionMediaAdapter;
        ImageView imageView;
        public ProductRatedViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProductAdapter = itemView.findViewById(R.id.nombreProductAdapter);
            valoracionMediaAdapter = itemView.findViewById(R.id.valoracionMediaAdapter);
            imageView = itemView.findViewById(R.id.imageViewRate);


        }
    }
}
