package com.example.proy1bueno.historicalPurchases.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Compra;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.historicalPurchases.ContractHistoricalPurchases;
import com.example.proy1bueno.historicalPurchases.data.DataCompra;
import com.example.proy1bueno.historicalPurchases.presenter.HistoricalPurchasesPresenter;
import com.example.proy1bueno.listProductsUser.data.DataProductLst;
import com.example.proy1bueno.listProductsUser.presenter.LstProductPresenter;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoricalPurchasesModel extends AppCompatActivity implements ContractHistoricalPurchases.Model{
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

    private static final String IP_BASE = "192.168.104.76:8080";
    private HistoricalPurchasesPresenter presenter;

    public HistoricalPurchasesModel(HistoricalPurchasesPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void historicalPurchasesAPI(Compra compra, OnHistoricalPurchasesListener onHistoricalPurchasesListener) {
        Log.e("HISTORICO API","ESTOY DENTRO");
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
   Log.e("Apiservice","Apiservice" + apiService);
        sharedPreferencesUserCFG = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataCompra> call;
        call = apiService.getHistoricalPurchases("COMPRA.HISTORICO",sharedPreferencesUserCFG.getInt("idUser",0));
        Log.e("Call en historico","Call= "+call.toString());
        Log.e("SALIENDO DEL HISTORICO API","SALGO");
        call.enqueue(new Callback<DataCompra>() {
            @Override
            public void onResponse(Call<DataCompra> call, Response<DataCompra> response) {
                Log.e("RESPONSE HISTORICO","ENTRAS AL RESPONSE???");
                if (response.isSuccessful()) {
                    DataCompra dataCompra = response.body();
                    ArrayList<Compra> lstCompras = dataCompra.getLstCompras();
                    Log.e("onResponse LSTCOMPRAS","VOY A LISTAR COMPRAS");
                    onHistoricalPurchasesListener.onFinished(lstCompras);
                }
            }

            @Override
            public void onFailure(Call<DataCompra> call, Throwable t) {
                Log.e("onFailure LSTPRODUCTS", "FALLO AL LISTAR", t);
            }
        });
    }
}
