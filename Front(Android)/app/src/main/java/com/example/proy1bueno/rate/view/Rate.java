package com.example.proy1bueno.rate.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.proy1bueno.MainActivity;
import com.example.proy1bueno.R;
import com.example.proy1bueno.addProduct.presenter.AddProductPresenter;
import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.listProductsUser.ContractLstProduct;
import com.example.proy1bueno.listProductsUser.view.LstProducts;
import com.example.proy1bueno.rate.ContractRate;
import com.example.proy1bueno.rate.presenter.RatePresenter;

import java.util.ArrayList;

public class Rate extends AppCompatActivity implements ContractRate.View {
    private RatingBar ratingBar;
    private ImageButton btnHome;
    float numEstrellas;
    private int idProductRate;
    private int idUser;
    SharedPreferences UserPreferences;

    Button btnRate;
    private RatePresenter presenter = new RatePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_product);
        initComponents();

    }

    private void initComponents(){
        UserPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        idUser = UserPreferences.getInt("idUser",0);
        Log.e("user_id","id user preferences = " + idUser);
        idProductRate = getIntent().getIntExtra("idProduct",0);
        Log.e("idProduct","id producto ratear = " + idProductRate);
        ratingBar = findViewById(R.id.ratingBar);
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                numEstrellas = rating;
//            }
//        });
//        Log.e("Valoracion Campos","Campos valoracion: " + idUser + " " + idProductRate + " " + numEstrellas);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, Categories.class);
            startActivity(intent);
        });

        btnRate = findViewById(R.id.btnRate);

        btnRate.setOnClickListener(view->{
            Valoracion valoracion = new Valoracion();
            valoracion.setIdUser(idUser);
            valoracion.setIdProduct(idProductRate);
            numEstrellas = ratingBar.getRating();
            valoracion.setNumEstrellas(numEstrellas);
            Toast.makeText(this, valoracion.toString(), Toast.LENGTH_SHORT).show();
            presenter.rate(valoracion);
            Intent intent = new Intent(this, Categories.class);
            startActivity(intent);
        });


    }


    @Override
    public void succesRate(Valoracion valoracion) {

    }

    @Override
    public void failureRate(String err) {

    }
}