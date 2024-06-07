package com.example.proy1bueno.productFile;

import com.example.proy1bueno.beans.Product;

import java.util.ArrayList;

public interface ContractProductFile {
    public interface Presenter{
        void productFile(Product product);
    }

    public interface Model{
        void productFileAPI(Product product,
                                 ContractProductFile.Model.OnProductFileListener onProductFileListener);

        interface OnProductFileListener {
            void onFinished(Product product);
            void onFailure(String err);
        }

    }
    public interface View{
        public void succesProductFile(Product product);
        void failureProductFile(String err);
    }
}
