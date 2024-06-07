package com.example.proy1bueno.userFilter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.proy1bueno.R;

import com.example.proy1bueno.adapters.PopularUsersAdapter;
import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.historicalPurchases.view.HistoricalPurchases;
import com.example.proy1bueno.listProductsUser.view.LstProducts;
import com.example.proy1bueno.lstBetterRates.view.LstBetterRates;
import com.example.proy1bueno.userFilter.ContractUserFilter;
import com.example.proy1bueno.userFilter.presenter.UserFilterPresenter;

import java.util.ArrayList;

public class UserFilter extends AppCompatActivity implements ContractUserFilter.View{

    RecyclerView recyclerView;


    private UserFilterPresenter presenter = new UserFilterPresenter(this);
    private static UserFilter userFilter = null;

    private static UserFilter getInstance(){
        if (userFilter == null){
            userFilter = new UserFilter();
        }
        return userFilter;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_filter);
        userFilter = this;
        ImageButton btnHomeFooter = findViewById(R.id.btnHomeFooter);
        ImageButton btnBetterRates = findViewById(R.id.btnBetterRates);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        ImageButton btnMostSells = findViewById(R.id.btnMostSells);
        ImageButton btnBuys = findViewById(R.id.btnBuys);
        btnHomeFooter.setOnClickListener(v -> volverHome());
        btnBetterRates.setOnClickListener(v -> abrirValoraciones());
        btnProfile.setOnClickListener(v -> abrirMisProductos());
        btnMostSells.setOnClickListener(v -> abrirUsuarioVentas());
        btnBuys.setOnClickListener(v -> abrirHistoricoCompras());
        initComponents();

    }
    private void volverHome(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }
    private void abrirValoraciones(){
        Intent intent = new Intent(this, LstBetterRates.class);
        startActivity(intent);
    }
    private void abrirUsuarioVentas(){
        Intent intent = new Intent(this, UserFilter.class);
        startActivity(intent);
    }
    private void abrirHistoricoCompras(){
        Intent intent = new Intent(this, HistoricalPurchases.class);
        startActivity(intent);
    }
    private void abrirMisProductos(){
        Intent intent = new Intent(this, LstProducts.class);
        startActivity(intent);
    }

    private void initComponents() {
        User user = new User();
        presenter.userFilter(user);
    }

//    public int dpToPx(int dp) {
//        float density = getResources().getDisplayMetrics().density;
//        return Math.round((float) dp * density);
//    }





    @Override
    public void successUserFilter(ArrayList<User> usersList) {
        recyclerView = findViewById(R.id.usersColumn);
        PopularUsersAdapter adapterUsers = new PopularUsersAdapter(usersList);
        recyclerView.setAdapter(adapterUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void failureUserFilter(String err) {

    }
}