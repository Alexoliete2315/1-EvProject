package com.example.proy1bueno.addProduct.model;

import android.util.Log;

import com.example.proy1bueno.addProduct.ContractAddProduct;
import com.example.proy1bueno.addProduct.data.DataProductAdd;
import com.example.proy1bueno.addProduct.presenter.AddProductPresenter;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductModel implements  ContractAddProduct.Model{

    private static final String IP_BASE = "192.168.104.76:8080";
    private AddProductPresenter presenter;
    public AddProductModel(AddProductPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void addProductsApi(Product product, OnAddProductListener onAddProductListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        Log.d("PRODUCT to add:", product.toString());

        Call<DataProductAdd> call = apiService.getDataAddProduct("PRODUCT.ADD", product.getIdProducto(), product.getNombreProducto(), product.getPrecioProducto(), product.getMarcaProducto(),
                product.getFechaSubidaProducto(),  product.getDescripcionProducto(), product.getImagenProducto(), product.getIdUser());
        call.enqueue(new Callback<DataProductAdd>() {
            @Override
            public void onResponse(Call<DataProductAdd> call, Response<DataProductAdd> response) {
                if (response.isSuccessful()){
                    DataProductAdd dataProductAdd = response.body();
                    ArrayList<Product> lstProducts = dataProductAdd.getLstProducts();
                    onAddProductListener.onFinished(lstProducts.get(0));
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
            public void onFailure(Call<DataProductAdd> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }





        });
    }
}
