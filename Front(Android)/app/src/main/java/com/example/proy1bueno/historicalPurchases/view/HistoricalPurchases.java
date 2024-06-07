package com.example.proy1bueno.historicalPurchases.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.example.proy1bueno.R;
import com.example.proy1bueno.adapters.CompraAdapter;
import com.example.proy1bueno.beans.Compra;
import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.historicalPurchases.ContractHistoricalPurchases;
import com.example.proy1bueno.historicalPurchases.presenter.HistoricalPurchasesPresenter;
import com.example.proy1bueno.listProductsUser.view.LstProducts;
import com.example.proy1bueno.lstBetterRates.view.LstBetterRates;
import com.example.proy1bueno.userFilter.view.UserFilter;

import java.util.ArrayList;

public class HistoricalPurchases extends AppCompatActivity implements ContractHistoricalPurchases.View{
    RecyclerView recyclerView;
    private HistoricalPurchasesPresenter presenter = new HistoricalPurchasesPresenter(this);

    private static HistoricalPurchases historicalPurchases = null;

    private static HistoricalPurchases getInstance(){
        if (historicalPurchases == null){
            historicalPurchases = new HistoricalPurchases();
        }
        return historicalPurchases;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_purchases);
        historicalPurchases = this;
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
        SharedPreferences userPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Log.e("user_id","id user preferences = " + userPreferences);
        Compra compra = new Compra();
        Log.e("Compra en initComponents","compra= "+compra);
        presenter.HistoricalPurchases(compra);

    }



    @Override
    public void successHistoricalPurchases(ArrayList<Compra> lstCompras) {
        recyclerView = findViewById(R.id.columnaListadoCompra);
        CompraAdapter compraAdapter = new CompraAdapter(lstCompras);
        recyclerView.setAdapter(compraAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void failureHistoricalPurchases(String err) {

    }
}