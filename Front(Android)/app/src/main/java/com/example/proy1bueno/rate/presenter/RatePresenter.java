package com.example.proy1bueno.rate.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.addProduct.ContractAddProduct;
import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.listProductsUser.ContractLstProduct;
import com.example.proy1bueno.rate.ContractRate;
import com.example.proy1bueno.rate.model.RateModel;

import java.util.ArrayList;

public class RatePresenter extends AppCompatActivity implements ContractRate.Presenter, ContractRate.Model.OnRateListener{
    private ContractRate.View view;
    private ContractRate.Model model;

    public RatePresenter(ContractRate.View view) {
        this.view = view;
        model = new RateModel(this, (Context) this.view);
    }

    @Override
    public void rate(Valoracion valoracion) {
        model.rateAPI(valoracion,this);
    }


    @Override
    public void onFinished(Valoracion valoracion) {
            view.succesRate(valoracion);
    }

    @Override
    public void onFailure(String err) {

    }
}
