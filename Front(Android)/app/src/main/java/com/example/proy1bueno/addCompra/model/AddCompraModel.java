package com.example.proy1bueno.addCompra.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.proy1bueno.addCompra.ContractAddCompra;

import com.example.proy1bueno.addCompra.data.DataAddCompra;
import com.example.proy1bueno.addCompra.presenter.AddCompraPresenter;

import com.example.proy1bueno.addProduct.data.DataProductAdd;
import com.example.proy1bueno.beans.Compra;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCompraModel implements  ContractAddCompra.Model{
    private SharedPreferences userPreferences;
    private Context context;
    private static final String IP_BASE = "192.168.104.76:8080";
    private AddCompraPresenter presenter;
    public AddCompraModel(AddCompraPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }
    @Override
    public void addCompraApi(Compra compra, OnAddCompraListener onAddCompraListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        Log.d("COMPRA to add:", compra.toString());
        userPreferences = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataAddCompra> call = apiService.getDataAddCompra("COMPRA.VENTA",userPreferences.getInt("idUser",0),compra.getIdProductoComprado());
        call.enqueue(new Callback<DataAddCompra>() {
            @Override
            public void onResponse(Call<DataAddCompra> call, Response<DataAddCompra> response) {
                if (response.isSuccessful()){
                    DataAddCompra dataAddCompra = response.body();
                    ArrayList<Compra> lstCompras = dataAddCompra.getLstCompras();
                    onAddCompraListener.onFinished(lstCompras.get(0));
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
            public void onFailure(Call<DataAddCompra> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });
    }
}
