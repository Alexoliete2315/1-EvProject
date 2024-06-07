package com.example.proy1bueno.listProductsUser.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.listProductsUser.ContractLstProduct;
import com.example.proy1bueno.listProductsUser.data.DataProductLst;
import com.example.proy1bueno.listProductsUser.presenter.LstProductPresenter;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LstProductModel extends AppCompatActivity implements ContractLstProduct.Model{
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

    private static final String IP_BASE = "192.168.104.76:8080";
    private LstProductPresenter presenter;

//   public LstProductModel(Context context){
//        this.context = context;
//   }
    public LstProductModel(LstProductPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void lstAPI(Product product, OnLstProductListener onLstProductListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").
                create(ApiService.class);
//        Log.e("Apiservice","Apiservice" + apiService);
        sharedPreferencesUserCFG = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataProductLst> call;
        call = apiService.getDataProductList("PRODUCT.LST",sharedPreferencesUserCFG.getInt("idUser",0));
        Log.e("call","call" + call.toString());
        call.enqueue(new Callback<DataProductLst>() {
            @Override
            public void onResponse(Call<DataProductLst> call, Response<DataProductLst> response) {
                if (response.isSuccessful()) {
                    DataProductLst dataProductLst = response.body();
                    ArrayList<Product> lstProducts = dataProductLst.getLstProducts();
                    Log.e("onResponse LSTPRODUCTS","VOY A LISTAR PRODUCTOS");
                    onLstProductListener.onFinished(lstProducts);


                }
            }

            @Override
            public void onFailure(Call<DataProductLst> call, Throwable t) {
                Log.e("onFailure LSTPRODUCTS", "FALLO AL LISTAR", t);
            }

        });
        }
}
