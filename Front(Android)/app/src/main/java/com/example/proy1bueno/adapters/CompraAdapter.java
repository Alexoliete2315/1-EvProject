package com.example.proy1bueno.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proy1bueno.R;
import com.example.proy1bueno.beans.Compra;

import java.util.ArrayList;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.CompraAdapterViewHolder> {
    ArrayList<Compra> lstCompras;
    Context context;

    public CompraAdapter(ArrayList<Compra> lstCompras) {
        this.lstCompras = lstCompras;
    }

    @NonNull
    @Override
    public CompraAdapter.CompraAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compra,parent,false);
        return new CompraAdapter.CompraAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompraAdapter.CompraAdapterViewHolder holder, int position) {
        holder.nombreProducto.setText(lstCompras.get(position).getProductoComprado());
        holder.marcaProducto.setText(lstCompras.get(position).getMarcaProducto());
        holder.precioProducto.setText(lstCompras.get(position).getPrecioProducto()+"€");
//        holder.vendedor.setText(lstCompras.get(position).getVendedor());
        holder.fecha.setText(lstCompras.get(position).getFechaCompra());
        String imageUrl = "http://192.168.104.76:8080/untitled/img/"+lstCompras.get(position).getImagenProducto();
        Glide.with(context).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lstCompras.size();
    }

    public class CompraAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView nombreProducto;
        TextView marcaProducto;
        TextView precioProducto;
//        TextView vendedor;
        TextView fecha;
        ImageView imageView;
        public CompraAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
//            ImageView imagenProducto = itemView.findViewById(R.id.imageViewAdapterCompra);
             nombreProducto = itemView.findViewById(R.id.nombreProductAdapterCompra);
             marcaProducto = itemView.findViewById(R.id.marcaAdapterCompra);
             precioProducto = itemView.findViewById(R.id.precioAdapterCompra);
//             vendedor = itemView.findViewById(R.id.vendedorAdapterCompra);
             fecha = itemView.findViewById(R.id.fechaAdapterCompra);
             imageView = itemView.findViewById(R.id.imageViewAdapterCompra);

        }
    }
}
