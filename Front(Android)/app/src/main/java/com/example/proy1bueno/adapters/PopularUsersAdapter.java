package com.example.proy1bueno.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proy1bueno.R;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.beans.User;

import java.util.ArrayList;

public class PopularUsersAdapter extends RecyclerView.Adapter<PopularUsersAdapter.PopularUserViewHolder> {

    ArrayList<User> lstUsers;
    Context context;
    public PopularUsersAdapter(ArrayList<User> lstUsers) {
        this.lstUsers = lstUsers;
    }
    @NonNull
    @Override
    public PopularUsersAdapter.PopularUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_users,parent,false);
        return new PopularUsersAdapter.PopularUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularUsersAdapter.PopularUserViewHolder holder, int position) {
        holder.nombreUserAdapter.setText(lstUsers.get(position).getUsername());
//        holder.idUser.setText(lstUsers.get(position).getIdUser());
        holder.numeroVentasAdapter.setText("Nº de ventas: " + lstUsers.get(position).getVenta());
        holder.itemView.setOnClickListener(v -> {
            User user = lstUsers.get(position);
        });
    }

    @Override
    public int getItemCount() {
        return lstUsers.size();
    }

    public class PopularUserViewHolder extends RecyclerView.ViewHolder{
        TextView nombreUserAdapter;
//        TextView idUser;
        TextView numeroVentasAdapter;

        public PopularUserViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreUserAdapter = itemView.findViewById(R.id.nombreUserAdapter);
//            idUser = itemView.findViewById(R.id.textView2);
            numeroVentasAdapter = itemView.findViewById(R.id.numeroVentasAdapter);
        }
    }
}
