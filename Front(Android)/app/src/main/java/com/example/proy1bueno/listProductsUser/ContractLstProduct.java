package com.example.proy1bueno.listProductsUser;

import com.example.proy1bueno.beans.Product;

import java.util.ArrayList;


public interface ContractLstProduct {
    public interface View{
       public void successLstProduct(ArrayList<Product> lstProducts);
      void failureLstProduct(String err);
//        void failureLogin(MyException err);

    }
    public interface Presenter{
        // void login(String email, String pass);
         void lstProducts(Product product);
        // void login(ViewUser viewUser);
        // VIEW-ORM
        // BEANS-ENTITIES
        // MVP - MVVM
    }
    public interface Model{
     interface OnLstProductListener{
           void onFinished(ArrayList<Product> lstProducts);
           void onFailure(String err);
        }
        void lstAPI(Product product,
                      ContractLstProduct.Model.OnLstProductListener onLstProductListener);
    }
}
