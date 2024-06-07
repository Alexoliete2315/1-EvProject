package com.example.proy1bueno.categoriesFilter.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.categoriesFilter.ContractCategoriesFilter;
import com.example.proy1bueno.categoriesFilter.data.DataCategoriesFilter;
import com.example.proy1bueno.categoriesFilter.presenter.CategoriesFilterPresenter;
import com.example.proy1bueno.listProductsUser.presenter.LstProductPresenter;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFilterModel implements ContractCategoriesFilter.Model{
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

    private static final String IP_BASE = "192.168.104.76:8080";
    private CategoriesFilterPresenter presenter;

    public CategoriesFilterModel(Context context, CategoriesFilterPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public void categoriesFilterAPI(Product product, ContractCategoriesFilter.Model.OnCategoriesFilterListener onCategoriesFilterListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        sharedPreferencesUserCFG = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataCategoriesFilter> call;

        if (!(product.getLstCategories().isEmpty())){
            String categories ="";
            for (String category:product.getLstCategories()) {
                categories += category+".";
            }
            //quito el ultimo punto del String
            categories = categories.substring(0,categories.length()-1);
            //SI HAY CATEGORIAS LAS PASO POR PARAMETRO AL BACK
            //PUEDEN SER VARIAS (FILTRO DINAMIC0)
            call = apiService.getCategories("PRODUCT.FILTER", categories);
        }else{
            call = apiService.getCategories("PRODUCT.FILTER", product.getCategory());

        }

        call.enqueue(new Callback<DataCategoriesFilter>() {
            @Override
            public void onResponse(Call<DataCategoriesFilter> call, Response<DataCategoriesFilter> response) {
                 if (response.isSuccessful()){
                     DataCategoriesFilter dataCategoriesFilter =response.body();
                     ArrayList<Product> lstProduct = dataCategoriesFilter.getLstProducts();
                     onCategoriesFilterListener.onFinished(lstProduct);
                 }else{
                     Log.e("Response Error", "HTTP state:38:" + response.code());
                     try{
                         String errorBody = response.errorBody().string();
                         Log.e("Response error", "Error body:41: " + errorBody);
                     }catch(IOException e){
                         e.printStackTrace();
                     }
                 }
            }

            @Override
            public void onFailure(Call<DataCategoriesFilter> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });
    }
}
