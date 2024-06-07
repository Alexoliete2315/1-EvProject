package com.example.proy1bueno.categoriesFilter.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.categoriesFilter.ContractCategoriesFilter;
import com.example.proy1bueno.categoriesFilter.model.CategoriesFilterModel;

import java.util.ArrayList;

public class CategoriesFilterPresenter extends AppCompatActivity implements ContractCategoriesFilter.Presenter, ContractCategoriesFilter.Model.OnCategoriesFilterListener {
    private ContractCategoriesFilter.View view;
    private ContractCategoriesFilter.Model model;

    public CategoriesFilterPresenter(ContractCategoriesFilter.View view) {
        this.view = view;
        model= new CategoriesFilterModel((Context) this.view,this);
    }

    @Override
    public void categoriesFilter(Product product) {
        model.categoriesFilterAPI(product,this);
    }

    @Override
    public void onFinished(ArrayList<Product> lstProducts) {
        view.succesCategoriesFilter(lstProducts);
    }

    @Override
    public void onFailure(String err) {

    }


}
