package com.example.proy1bueno.categoriesFilter;

import com.example.proy1bueno.beans.Product;

import java.util.ArrayList;

public interface ContractCategoriesFilter {
    public interface Presenter{
        void categoriesFilter(Product product);
    }

    public interface Model{
        void categoriesFilterAPI(Product product,
                                       ContractCategoriesFilter.Model.OnCategoriesFilterListener onCategoriesFilterListener);

        interface OnCategoriesFilterListener {
            void onFinished(ArrayList<Product> lstProducts);
            void onFailure(String err);
        }

    }
    public interface View{
        public void succesCategoriesFilter(ArrayList<Product> lstProducts);
        void failureCategoriesFilter(String err);
    }
}
