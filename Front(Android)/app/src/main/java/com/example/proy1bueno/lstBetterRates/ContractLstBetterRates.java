package com.example.proy1bueno.lstBetterRates;


import com.example.proy1bueno.beans.Valoracion;


import java.util.ArrayList;

public interface ContractLstBetterRates {
    public interface Presenter{
        void lstBetterRates(Valoracion valoracion);
    }

    public interface Model{
        void lstBetterRatesAPI(Valoracion valoracion,
                           ContractLstBetterRates.Model.OnLstBetterRates onLstBetterRates);

        interface OnLstBetterRates {
            void onFinished(ArrayList<Valoracion> lstValoraciones);
            void onFailure(String err);
        }

    }
    public interface View{
        public void succesLstBetterRates(ArrayList<Valoracion> lstValoraciones);
        void failureLstBetterRates(String err);
    }
}
