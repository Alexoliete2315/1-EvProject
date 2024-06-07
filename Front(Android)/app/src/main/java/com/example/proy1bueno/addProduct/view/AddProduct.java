package com.example.proy1bueno.addProduct.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proy1bueno.R;
import com.example.proy1bueno.addProduct.ContractAddProduct;
import com.example.proy1bueno.addProduct.presenter.AddProductPresenter;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.listProductsUser.view.LstProducts;

public class AddProduct extends AppCompatActivity implements ContractAddProduct.View{
    SharedPreferences sharedPreferencesUserCFG;
    EditText idProducto;
    EditText nombreProducto;
    EditText precioProducto;
    EditText marcaProducto;
    EditText fechaSubidaproducto;
    EditText descripcionProducto;
    EditText imagenProducto;
    int idUser;

    Button btnRetunProducts;
    Button btnAddProduct;
    private AddProductPresenter presenter = new AddProductPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initComponents();

    }
    public void initComponents(){
        sharedPreferencesUserCFG = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        idProducto = findViewById(R.id.editTextIdProduct);
        nombreProducto = findViewById(R.id.editTextProductName);
        precioProducto = findViewById(R.id.editTextPriceProduct);
        marcaProducto = findViewById(R.id.editTextMarcaProduct);
        fechaSubidaproducto = findViewById(R.id.editTextDateUpload);
        descripcionProducto = findViewById(R.id.editTextDescriptionProduct);
        imagenProducto = findViewById(R.id.editTextImageProduct);
        idUser = sharedPreferencesUserCFG.getInt("id",0);

        btnRetunProducts = findViewById(R.id.btnRetunProducts);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnRetunProducts.setOnClickListener(view -> {
            Intent intent = new Intent(this, LstProducts.class);
            startActivity(intent);
        });

        btnAddProduct.setOnClickListener(view->{
            Toast.makeText(this, "Add Product", Toast.LENGTH_SHORT).show();
            Product product = new Product();
            product.setIdProducto(Integer.parseInt(idProducto.getText().toString()));
            product.setNombreProducto(nombreProducto.getText().toString());
            product.setPrecioProducto(Double.parseDouble(precioProducto.getText().toString()));
            product.setMarcaProducto(marcaProducto.getText().toString());
            product.setFechaSubidaProducto(fechaSubidaproducto.getText().toString());
            product.setDescripcionProducto(descripcionProducto.getText().toString());
            product.setImagenProducto(imagenProducto.getText().toString());
            product.setIdUser(idUser);
            Toast.makeText(this,product.toString(),Toast.LENGTH_SHORT);
            presenter.addProduct(product);
            Log.e("Product Data (Onclick add)",product.toString());
            Intent intent = new Intent(this, LstProducts.class);
            startActivity(intent);
        });
    }

    @Override
    public void successAddProduct(Product product) {

    }

    @Override
    public void failureMovies(String err) {

    }
}