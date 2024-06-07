package com.example.proy1bueno.lstBetterRates.presenter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.lstBetterRates.ContractLstBetterRates;
import com.example.proy1bueno.lstBetterRates.model.LstBetterRatesModel;
import com.example.proy1bueno.userFilter.ContractUserFilter;
import com.example.proy1bueno.userFilter.model.UserFilterModel;

import java.util.ArrayList;

public class LstBetterRatesPresenter extends AppCompatActivity implements ContractLstBetterRates.Presenter, ContractLstBetterRates.Model.OnLstBetterRates{
    private ContractLstBetterRates.View view;
    private ContractLstBetterRates.Model model;
    public LstBetterRatesPresenter(ContractLstBetterRates.View view){
        this.view = view;
        model = new LstBetterRatesModel(this);
    }

    @Override
    public void lstBetterRates(Valoracion valoracion) {
        model.lstBetterRatesAPI(valoracion,this);
    }

    @Override
    public void onFinished(ArrayList<Valoracion> lstValoraciones) {
        view.succesLstBetterRates(lstValoraciones);
    }

    @Override
    public void onFailure(String err) {

    }


}
