package com.example.proy1bueno.productFile.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Product;

import com.example.proy1bueno.productFile.ContractProductFile;
import com.example.proy1bueno.productFile.model.ProductFileModel;
import com.example.proy1bueno.productFile.view.ProductFile;

public class ProductFilePresenter extends AppCompatActivity implements ContractProductFile.Presenter, ContractProductFile.Model.OnProductFileListener{
    private ContractProductFile.View view;
    private ContractProductFile.Model model;

    public ProductFilePresenter(ContractProductFile.View view) {
        this.view = view;
        model = new ProductFileModel(this, (Context) this.view);
    }

    @Override
    public void productFile(Product product) {
        model.productFileAPI(product, this);
    }

    @Override
    public void onFinished(Product product) {
        view.succesProductFile(product);
    }

    @Override
    public void onFailure(String err) {

    }
}
