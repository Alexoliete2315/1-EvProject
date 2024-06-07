package com.example.proy1bueno.productFile.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.proy1bueno.R;
import com.example.proy1bueno.addCompra.view.AddCompra;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.historicalPurchases.view.HistoricalPurchases;
import com.example.proy1bueno.listProductsUser.view.LstProducts;
import com.example.proy1bueno.lstBetterRates.view.LstBetterRates;
import com.example.proy1bueno.productFile.ContractProductFile;
import com.example.proy1bueno.productFile.presenter.ProductFilePresenter;
import com.example.proy1bueno.rate.view.Rate;
import com.example.proy1bueno.userFilter.view.UserFilter;
import com.google.android.material.button.MaterialButton;

public class ProductFile extends AppCompatActivity implements ContractProductFile.View {
    private TextView nombreTextView;
    private TextView precioTextView;
    private TextView marcaTextView;
    private TextView vendedorTextView;
    private TextView descripcionTextView;







    //    private MaterialButton btnRateSmall;
    private MaterialButton btnRateHuge;
    private MaterialButton btnBuy;

    private int idProduct;

    private ProductFilePresenter productFilePresenter = new ProductFilePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_file);
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

    public void initComponents(){
        nombreTextView = findViewById(R.id.nombreProductFile);
        precioTextView = findViewById(R.id.precioProductFile);
        marcaTextView = findViewById(R.id.marcaProductFile);
        vendedorTextView = findViewById(R.id.vendedorProductFile);
        descripcionTextView = findViewById(R.id.descripcionProductFile);
        Product product = new Product();
        idProduct = getIntent().getIntExtra("idProduct", 0);
        product.setIdProducto(idProduct);
        productFilePresenter.productFile(product);
//        btnRateSmall = findViewById(R.id.btnRateSmall);
        btnRateHuge = findViewById(R.id.btnRateHuge);
//        btnRateSmall.setOnClickListener(v -> abrirRate());
        btnRateHuge.setOnClickListener(v -> abrirRate());
        btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(v -> abrirCompra());

    }

    @Override
    public void succesProductFile(Product product) {
        // Aquí puedes imprimir los datos del producto
        Log.e("SUCCES FILE","LLEGO AL SUCCES PRINTO PRODUCT= "+ product);
        imprimirDatosProductoEncontrado(product);
    }

    @Override
    public void failureProductFile(String err) {
        // Aquí puedes manejar el fallo en la obtención del producto si es necesario
    }

    // Método para imprimir datos del producto
    private void imprimirDatosProductoEncontrado(Product product) {
        if (product != null) {
            Log.e("IMPRIMIR DATOS","Los datos son= " + product.toString());
            // Establecer datos en los TextViews con el producto
            nombreTextView.setText(product.getNombreProducto());
            precioTextView.setText(String.valueOf(product.getPrecioProducto())+"€");
            marcaTextView.setText(product.getMarcaProducto());
            vendedorTextView.setText(product.getVendedor());
            descripcionTextView.setText(product.getDescripcionProducto());
            ConstraintLayout constraintLayout = findViewById(R.id.imgProductFile);
            String imageUrl = "http://192.168.104.76:8080/untitled/img/"+product.getImagenProducto();
            Glide.with(this)
                    .load(imageUrl)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            constraintLayout.setBackground(resource);
                        }
                    });

        }
    }
    public void abrirRate(){
        Intent intent = new Intent(this, Rate.class);
        intent.putExtra("idProduct", idProduct);
        startActivity(intent);
    }
    public void abrirCompra(){
        Intent intent = new Intent(this, AddCompra.class);
        intent.putExtra("idProduct", idProduct);
        startActivity(intent);
    }
}
