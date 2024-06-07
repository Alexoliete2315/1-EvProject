package com.example.proy1bueno.historicalPurchases;

import com.example.proy1bueno.beans.Compra;


import java.util.ArrayList;

public interface ContractHistoricalPurchases {
    public interface View{
        public void successHistoricalPurchases(ArrayList<Compra> lstCompras);
        void failureHistoricalPurchases(String err);
//        void failureLogin(MyException err);

    }
    public interface Presenter{
        // void login(String email, String pass);
        void HistoricalPurchases(Compra compra);
        // void login(ViewUser viewUser);
        // VIEW-ORM
        // BEANS-ENTITIES
        // MVP - MVVM
    }
    public interface Model{
        interface OnHistoricalPurchasesListener{
            void onFinished(ArrayList<Compra> lstCompras);
            void onFailure(String err);
        }
        void historicalPurchasesAPI(Compra compra,
                    ContractHistoricalPurchases.Model.OnHistoricalPurchasesListener onHistoricalPurchasesListener);
    }
}
