package com.example.proy1bueno.listProductsUser.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.listProductsUser.ContractLstProduct;
import com.example.proy1bueno.listProductsUser.model.LstProductModel;

import java.util.ArrayList;


public class LstProductPresenter extends AppCompatActivity implements ContractLstProduct.Presenter, ContractLstProduct.Model.OnLstProductListener{
    private ContractLstProduct.View view;
    private ContractLstProduct.Model model;

    public LstProductPresenter(ContractLstProduct.View view){
        this.view = view;
       model = new LstProductModel(this, (Context) this.view);
    }
    @Override
    public void lstProducts(Product product) {

        model.lstAPI(product, this);
    }


    @Override
    public void onFinished(ArrayList<Product> lstProducts) {

        view.successLstProduct(lstProducts);
    }

    @Override
    public void onFailure(String err) {

    }
}
