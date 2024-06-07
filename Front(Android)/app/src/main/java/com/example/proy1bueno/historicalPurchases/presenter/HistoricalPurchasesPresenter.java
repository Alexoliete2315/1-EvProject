package com.example.proy1bueno.historicalPurchases.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Compra;
import com.example.proy1bueno.historicalPurchases.ContractHistoricalPurchases;
import com.example.proy1bueno.historicalPurchases.model.HistoricalPurchasesModel;

import java.util.ArrayList;

public class HistoricalPurchasesPresenter extends AppCompatActivity implements ContractHistoricalPurchases.Presenter,
        ContractHistoricalPurchases.Model.OnHistoricalPurchasesListener{
    private ContractHistoricalPurchases.View view;
    private ContractHistoricalPurchases.Model model;

    public HistoricalPurchasesPresenter(ContractHistoricalPurchases.View view) {
        this.view = view;
        model = new HistoricalPurchasesModel(this, (Context) this.view);
    }

    @Override
    public void HistoricalPurchases(Compra compra) {

        model.historicalPurchasesAPI(compra,this);
    }

    @Override
    public void onFinished(ArrayList<Compra> lstCompras) {
        view.successHistoricalPurchases(lstCompras);
    }

    @Override
    public void onFailure(String err) {

    }
}
