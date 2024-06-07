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
import com.example.proy1bueno.productFile.view.ProductFile;


import java.util.ArrayList;

public class ProductPerUserAdapter extends RecyclerView.Adapter<ProductPerUserAdapter.ProductPerUserViewHolder>{

    ArrayList<Product> lstProduct;
    Context context;

    public ProductPerUserAdapter(ArrayList<Product> lstProduct) {
        this.lstProduct = lstProduct;
    }
    @NonNull
    @Override
    public ProductPerUserAdapter.ProductPerUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductPerUserAdapter.ProductPerUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductPerUserAdapter.ProductPerUserViewHolder holder, int position) {
        holder.nombreProduct.setText(lstProduct.get(position).getNombreProducto());
        holder.marcaProduct.setText(lstProduct.get(position).getMarcaProducto());
//        holder.descripcion.setText(lstProduct.get(position).getDescripcionProducto());
        String imageUrl = "http://192.168.104.76:8080/untitled/img/"+lstProduct.get(position).getImagenProducto();
        Glide.with(context).load(imageUrl).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Product product = lstProduct.get(position);
            int idProduct = product.getIdProducto();
            Intent intent = new Intent(holder.itemView.getContext(), ProductFile.class);
            intent.putExtra("idProduct", idProduct);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public class ProductPerUserViewHolder extends RecyclerView.ViewHolder{
        TextView nombreProduct;
        TextView marcaProduct;
//        TextView descripcion;
        ImageView imageView;
        public ProductPerUserViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProduct = itemView.findViewById(R.id.nombreAdapter);
            marcaProduct = itemView.findViewById(R.id.marcaAdapter);
//            descripcion = itemView.findViewById(R.id.descripcionAdapter);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
