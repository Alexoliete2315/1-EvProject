package com.example.proy1bueno.listProductsUser.view;

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
import com.example.proy1bueno.adapters.ProductPerUserAdapter;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.historicalPurchases.view.HistoricalPurchases;
import com.example.proy1bueno.listProductsUser.ContractLstProduct;
import com.example.proy1bueno.listProductsUser.presenter.LstProductPresenter;
import com.example.proy1bueno.loginUser.view.LoginUser;
import com.example.proy1bueno.lstBetterRates.view.LstBetterRates;
import com.example.proy1bueno.userFilter.view.UserFilter;

import java.util.ArrayList;

public class LstProducts extends AppCompatActivity implements ContractLstProduct.View{
      ImageButton btnLogout;
//    Button btnAddProduct;
//    Button btnRate;
    RecyclerView recyclerView;
    private LstProductPresenter presenter = new LstProductPresenter(this);

    private static LstProducts lstProducts = null;

    private static LstProducts getInstance(){
        if (lstProducts == null){
            lstProducts = new LstProducts();
        }
        return lstProducts;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_products);
        lstProducts = this;
        ImageButton btnHomeFooter = findViewById(R.id.btnHomeFooter);
        ImageButton btnBetterRates = findViewById(R.id.btnBetterRates);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        ImageButton btnMostSells = findViewById(R.id.btnMostSells);
        ImageButton btnBuys = findViewById(R.id.btnBuys);
        btnLogout = findViewById(R.id.btnLogOut);
        btnLogout.setOnClickListener(v -> logOut());
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
    private void logOut(){
        SharedPreferences UserPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = UserPreferences.edit();
        editor.remove("LogCheck");
        editor.remove("username");
        editor.remove("idUser");
        Log.e("Preferences delete","borro credenciales" );
        editor.apply();
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
    }

    private void initComponents() {
        SharedPreferences UserPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Log.e("user_id","id user preferences = " + UserPreferences);
        Product product = new Product();
        Log.e("Product en init","Product= " + product);
        presenter.lstProducts(product);
    }


    @Override
    public void successLstProduct(ArrayList<Product> lstProducts) {

        recyclerView = findViewById(R.id.columnaListado);
        ProductPerUserAdapter productPerUserAdapter = new ProductPerUserAdapter(lstProducts);
        recyclerView.setAdapter(productPerUserAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
        @Override
    public void failureLstProduct(String err) {

    }
}