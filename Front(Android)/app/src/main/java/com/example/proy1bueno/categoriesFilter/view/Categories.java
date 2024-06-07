package com.example.proy1bueno.categoriesFilter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proy1bueno.R;
import com.example.proy1bueno.adapters.ProductAdapter;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.categoriesFilter.ContractCategoriesFilter;
import com.example.proy1bueno.categoriesFilter.presenter.CategoriesFilterPresenter;
import com.example.proy1bueno.historicalPurchases.view.HistoricalPurchases;
import com.example.proy1bueno.listProductsUser.view.LstProducts;
import com.example.proy1bueno.lstBetterRates.view.LstBetterRates;
import com.example.proy1bueno.userFilter.view.UserFilter;

import java.util.ArrayList;

public class Categories extends AppCompatActivity implements ContractCategoriesFilter.View{
    private SharedPreferences sharedPreferences;

    Button btnHombre;
    Button btnMujer;
    Button btnCamisetas;
    Button btnPantalones;
    private SearchView searchView;
    ProductAdapter adapterProduct;

    //boton para limpriar los filtros a la vez
//    Button btnClear;

    //necesito:
    // Array de categorias y de productos
    //presenter
    //declaro la actividad = null
    private ArrayList<String>categories;
     ArrayList<Product>lstProducts;
    private CategoriesFilterPresenter presenter = new CategoriesFilterPresenter(this);
//    private static Categories categoriesActivity = null;

    //Uso RecyclerView
    //Paso losproductos al recycler
    private RecyclerView recyclerView;
    private ArrayList<Product> RecyclerLstProducts;

//    public static Categories getInstance() {
//        if (categoriesActivity == null){
//            categoriesActivity = new Categories();
//        }
//        return categoriesActivity;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
//        categoriesActivity=this;
        sharedPreferences = getSharedPreferences("com.MyApp.PRODUCTS",MODE_PRIVATE);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
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

    private void filterList(String text) {
        ArrayList<Product>RecyclerLstProductsFiltered = new ArrayList<>();
        for (Product product: lstProducts) {
            if(product.getNombreProducto().toLowerCase().contains(text.toLowerCase())){
                RecyclerLstProductsFiltered.add(product);
            }
        }
    if (RecyclerLstProductsFiltered.isEmpty()){
        Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
    }else{
        adapterProduct.setRecyclerLstProductsFiltered(RecyclerLstProductsFiltered);
        }
    }

    private void initComponents(){
        //Arrays
        categories = new ArrayList<>();
        Product product = new Product();
        product.setCategory("");
        btnHombre = findViewById(R.id.btnHombre);
        btnMujer = findViewById(R.id.btnMujer);
        btnCamisetas = findViewById(R.id.btnCamisetas);
        btnPantalones = findViewById(R.id.btnPantalones);

        //onClick compruebo si el filtro ya estaba añadido
        //en caso deque lo este al clicar otra vez lo elimino
        btnHombre.setOnClickListener(v -> {
            if (categories.contains("Hombre")){
                categories.remove("Hombre");
            }else{
                categories.add("Hombre");
            }
            //seteo el filtro en los productos
            product.setLstCategories(categories);
            //llamo al presenter para que ejecute
            presenter.categoriesFilter(product);
        });
        btnMujer.setOnClickListener(v -> {
            if (categories.contains("Mujer")){
                categories.remove("Mujer");
            }else{
                categories.add("Mujer");
            }
            //seteo el filtro en los productos
            product.setLstCategories(categories);
            //llamo al presenter para que ejecute
            presenter.categoriesFilter(product);
        });
        btnCamisetas.setOnClickListener(v -> {
            if (categories.contains("Camisetas")){
                categories.remove("Camisetas");
            }else{
                categories.add("Camisetas");
            }
            //seteo el filtro en los productos
            product.setLstCategories(categories);
            //llamo al presenter para que ejecute
            presenter.categoriesFilter(product);
        });
        btnPantalones.setOnClickListener(v -> {
            if (categories.contains("Pantalones")){
                categories.remove("Pantalones");
            }else{
                categories.add("Pantalones");
            }
            //seteo el filtro en los productos
            product.setLstCategories(categories);
            //llamo al presenter para que ejecute
            presenter.categoriesFilter(product);
        });
        //vuelvo a llamar al presenter para que ejecute los filtros en cualquier caso
        //(sea quitar filtros o ponerlos)
        presenter.categoriesFilter(product);
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

    @Override
    public void succesCategoriesFilter(ArrayList<Product> lstProducts) {
        this.lstProducts = lstProducts;
        Log.e("SUCCES CATEGORIES FILTER","HE LLEGADO AL SUCCES DEL FILTRO CON ESTOS DATOS= " +
                lstProducts + "\n Longuitud de la cadena" + lstProducts.size());
        recyclerView = findViewById(R.id.columnaListado);
        adapterProduct = new ProductAdapter(lstProducts);
        recyclerView.setAdapter(adapterProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void failureCategoriesFilter(String err) {

    }
}