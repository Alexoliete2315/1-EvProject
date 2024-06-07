package com.example.proy1bueno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.os.Handler;
import android.widget.Button;

import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.listProductsUser.view.LstProducts;
import com.example.proy1bueno.loginUser.view.LoginUser;
import com.example.proy1bueno.lstBetterRates.view.LstBetterRates;
import com.example.proy1bueno.userFilter.view.UserFilter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Retrasar el cambio de actividad después de 3000 milisegundos (3 segundos)
        new Handler().postDelayed(() -> {
            abrirLoginActivity();
            // Cierra la actividad actual
            finish();
        }, 3000); // 3000 milisegundos (3 segundos)
    }

    private void abrirLoginActivity() {
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
    }

}