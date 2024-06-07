package com.example.proy1bueno.rate.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.addProduct.data.DataProductAdd;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.listProductsUser.data.DataProductLst;
import com.example.proy1bueno.listProductsUser.presenter.LstProductPresenter;
import com.example.proy1bueno.rate.ContractRate;
import com.example.proy1bueno.rate.data.DataRate;
import com.example.proy1bueno.rate.presenter.RatePresenter;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateModel extends AppCompatActivity implements ContractRate.Model{

    private SharedPreferences userPreferences;
    private Context context;

    private static final String IP_BASE = "192.168.104.76:8080";
    private RatePresenter presenter;

    public RateModel(RatePresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }
    @Override
    public void rateAPI(Valoracion valoracion, OnRateListener onRateListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").
                create(ApiService.class);
//        Log.e("Apiservice","Apiservice" + apiService);
        userPreferences = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataRate> call;

        /*
        2 OPTIONS
        @GET("MyServlet")
        Call<DataRate> getAddValoracion(@Query("ACTION")String action);
        @GET("MyServlet")
        Call<DataRate> getAddValoracionComplete(@Query("ACTION")String action,
                                                @Query("idUser")int idUser,
                                                @Query("idProducto")int idProducto,
                                                @Query("numEstrellas")float numEstrellas);
         */
        call = apiService.getAddValoracionComplete("RATE.ADD", valoracion.getIdUser(), valoracion.getIdProduct(), valoracion.getNumEstrellas());
        Log.e("call","call" + call.toString());

        call.enqueue(new Callback<DataRate>() {
            @Override
            public void onResponse(Call<DataRate> call, Response<DataRate> response) {
                if (response.isSuccessful()){
                    DataRate dataRate = response.body();
                    ArrayList<Valoracion> lstValoraciones = dataRate.getLstValoraciones();
                    onRateListener.onFinished(lstValoraciones.get(0));
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
            public void onFailure(Call<DataRate> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });
    }
}
