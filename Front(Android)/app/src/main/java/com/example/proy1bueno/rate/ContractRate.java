package com.example.proy1bueno.rate;
import com.example.proy1bueno.beans.Valoracion;


import java.util.ArrayList;

public interface ContractRate {
    public interface View{
        public void succesRate(Valoracion valoracion);
        void failureRate(String err);
//        void failureLogin(MyException err);

    }
    public interface Presenter{
        // void login(String email, String pass);
        void rate(Valoracion valoracion);
        // void login(ViewUser viewUser);
        // VIEW-ORM
        // BEANS-ENTITIES
        // MVP - MVVM
    }
    public interface Model{
        interface OnRateListener{
            void onFinished(Valoracion valoracion);
            void onFailure(String err);
        }
        void rateAPI(Valoracion valoracion,
                    ContractRate.Model.OnRateListener onRateListener);
    }
}
