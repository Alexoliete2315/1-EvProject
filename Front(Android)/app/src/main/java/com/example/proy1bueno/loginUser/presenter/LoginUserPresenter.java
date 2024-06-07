package com.example.proy1bueno.loginUser.presenter;

import android.util.Log;

import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.loginUser.ContractLoginUser;
import com.example.proy1bueno.loginUser.model.LoginUserModel;

public class LoginUserPresenter implements ContractLoginUser.Presenter, ContractLoginUser.Model.OnLoginUserListener {
    private ContractLoginUser.View view;
    private ContractLoginUser.Model model;

    public LoginUserPresenter(ContractLoginUser.View view){
        this.view = view;
        model = new LoginUserModel(this);
    }
    @Override
    public void login(User user) {model.loginAPI(user, this);}


    @Override
    public void onFinished(User user) {
        view.successLogin(user);
        Log.e("OnFinished","Succes?" + user.toString());
    }

    @Override
    public void onFailure(String err) {

    }

}
