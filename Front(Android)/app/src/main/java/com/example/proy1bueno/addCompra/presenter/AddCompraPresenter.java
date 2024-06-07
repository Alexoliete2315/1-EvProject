package com.example.proy1bueno.addCompra.presenter;

import android.content.Context;

import com.example.proy1bueno.addCompra.ContractAddCompra;
import com.example.proy1bueno.addCompra.model.AddCompraModel;
import com.example.proy1bueno.beans.Compra;

public class AddCompraPresenter implements ContractAddCompra.Presenter, ContractAddCompra.Model.OnAddCompraListener{
    private ContractAddCompra.View view;
    private ContractAddCompra.Model model;

    public AddCompraPresenter(ContractAddCompra.View view) {
        this.view = view;
        model = new AddCompraModel(this, (Context) this.view);
    }

    @Override
    public void addCompra(Compra compra) {
        model.addCompraApi(compra, this);
    }

    @Override
    public void onFinished(Compra compra) {
        view.successAddCompra(compra);
    }

    @Override
    public void onFailure(String err) {

    }
}
