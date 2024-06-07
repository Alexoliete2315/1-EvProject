package com.example.proy1bueno.addProduct;

import com.example.proy1bueno.beans.Product;

public interface ContractAddProduct {
    public interface Presenter{
        void addProduct(Product product);
    }

    public interface Model{
        void addProductsApi(Product product,
                            OnAddProductListener onAddProductListener);

        interface OnAddProductListener{
            void onFinished(Product product);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successAddProduct(Product product);
        void failureMovies(String err);
    }
}
