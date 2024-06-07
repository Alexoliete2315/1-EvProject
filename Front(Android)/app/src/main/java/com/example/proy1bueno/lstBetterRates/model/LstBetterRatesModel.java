package com.example.proy1bueno.lstBetterRates.model;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.lstBetterRates.ContractLstBetterRates;
import com.example.proy1bueno.lstBetterRates.data.DataLstBetterRates;
import com.example.proy1bueno.lstBetterRates.presenter.LstBetterRatesPresenter;
import com.example.proy1bueno.userFilter.data.DataUserFilter;
import com.example.proy1bueno.userFilter.presenter.UserFilterPresenter;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LstBetterRatesModel extends AppCompatActivity implements ContractLstBetterRates.Model{
    private static final String IP_BASE = "192.168.104.76:8080";
    //    private static final String IP_BASE = "192.168.1.196:8080";
    private LstBetterRatesPresenter presenter;

    public LstBetterRatesModel(LstBetterRatesPresenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void lstBetterRatesAPI(Valoracion valoracion, OnLstBetterRates onLstBetterRates) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);

        Call<DataLstBetterRates> call;
        call = apiService.getLstBetterRates("RATE.FILTER","productBetterRate");

        call.enqueue(new Callback<DataLstBetterRates>() {
            @Override
            public void onResponse(Call<DataLstBetterRates> call, Response<DataLstBetterRates> response) {
                if (response.isSuccessful()){
                    Log.e("On Response","isSuccesfull");
                    DataLstBetterRates dataLstBetterRates = response.body();
                    Log.e("DATA FILTER","QUE OSTIAS HAY AQUI?" + dataLstBetterRates.getLstValoraciones());
                    ArrayList<Valoracion> lstValoraciones = dataLstBetterRates.getLstValoraciones();
                    Log.e("Response Success","USERLIST " + lstValoraciones.toString());
                    onLstBetterRates.onFinished(lstValoraciones);
                }else{
                    Log.e("Response Error", "ERROR HTTP" + response.code());
                    try{
                        String errorBody = response.errorBody().string();
                        Log.e("Response error", "ERROR BODY " + errorBody);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataLstBetterRates> call, Throwable t) {

            }
        });
    }
}
