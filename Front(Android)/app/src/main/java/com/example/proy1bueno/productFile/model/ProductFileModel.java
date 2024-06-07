package com.example.proy1bueno.productFile.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Product;

import com.example.proy1bueno.listProductsUser.data.DataProductLst;
import com.example.proy1bueno.listProductsUser.presenter.LstProductPresenter;
import com.example.proy1bueno.productFile.ContractProductFile;
import com.example.proy1bueno.productFile.data.DataProductFile;
import com.example.proy1bueno.productFile.presenter.ProductFilePresenter;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFileModel extends AppCompatActivity implements ContractProductFile.Model{
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

    private static final String IP_BASE = "192.168.104.76:8080";
    private ProductFilePresenter presenter;

    public ProductFileModel(ProductFilePresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void productFileAPI(Product product, OnProductFileListener onProductFileListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").
                create(ApiService.class);
//        Log.e("Apiservice","Apiservice" + apiService);
        sharedPreferencesUserCFG = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataProductFile> call;
        call = apiService.getProductFile("PRODUCT.FILE", product.getIdProducto());
        call.enqueue(new Callback<DataProductFile>() {
            @Override
            public void onResponse(Call<DataProductFile> call, Response<DataProductFile> response) {
                if (response.isSuccessful()){
                    DataProductFile dataProductFile = response.body();
                    ArrayList<Product> lstProducts = dataProductFile.getLstProducts();
                    Log.e("RESPONSE SUCCESFULL","QUE PRODUCTO ENTRA?"+ lstProducts);
                    onProductFileListener.onFinished(lstProducts.get(0));
                    Log.e("entarda datos productfile","que llevas" + lstProducts.get(0).toString());
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
            public void onFailure(Call<DataProductFile> call, Throwable t) {

            }
        });


    }
}
