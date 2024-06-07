package com.example.proy1bueno.addProduct.presenter;

import com.example.proy1bueno.addProduct.ContractAddProduct;
import com.example.proy1bueno.addProduct.model.AddProductModel;
import com.example.proy1bueno.beans.Product;

public class AddProductPresenter implements ContractAddProduct.Presenter, ContractAddProduct.Model.OnAddProductListener{
    private ContractAddProduct.View view;
    private ContractAddProduct.Model model;

    public AddProductPresenter(ContractAddProduct.View view){
        this.view=view;
        model = new AddProductModel(this);
    }

    @Override
    public void addProduct(Product product) {
        model.addProductsApi(product, this);
    }

    @Override
    public void onFinished(Product product) {
        view.successAddProduct(product);
    }

    @Override
    public void onFailure(String err) {

    }
}
