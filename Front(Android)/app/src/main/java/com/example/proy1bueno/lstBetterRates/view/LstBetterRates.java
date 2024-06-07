package com.example.proy1bueno.lstBetterRates.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.proy1bueno.R;
import com.example.proy1bueno.adapters.ProductRatedAdapter;
import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.historicalPurchases.view.HistoricalPurchases;
import com.example.proy1bueno.listProductsUser.view.LstProducts;
import com.example.proy1bueno.lstBetterRates.ContractLstBetterRates;
import com.example.proy1bueno.lstBetterRates.presenter.LstBetterRatesPresenter;
import com.example.proy1bueno.userFilter.view.UserFilter;


import java.util.ArrayList;

public class LstBetterRates extends AppCompatActivity implements ContractLstBetterRates.View{
    RecyclerView recyclerView;



    private LstBetterRatesPresenter presenter = new LstBetterRatesPresenter(this);
    private static LstBetterRates lstBetterRates = null;

    private static LstBetterRates getInstance(){
        if (lstBetterRates == null){
            lstBetterRates = new LstBetterRates();
        }
        return lstBetterRates;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_better_rates);
        lstBetterRates = this;
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
        Valoracion valoracion = new Valoracion();
        presenter.lstBetterRates(valoracion);
    }

    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public void succesLstBetterRates(ArrayList<Valoracion> lstValoraciones) {
        recyclerView = findViewById(R.id.productsColumn);
        ProductRatedAdapter productRatedAdapter = new ProductRatedAdapter(lstValoraciones);
        recyclerView.setAdapter(productRatedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void failureLstBetterRates(String err) {

    }
}