package com.example.proy1bueno.userFilter.presenter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.userFilter.ContractUserFilter;
import com.example.proy1bueno.userFilter.model.UserFilterModel;

import java.util.ArrayList;

public class UserFilterPresenter  extends AppCompatActivity implements ContractUserFilter.Presenter, ContractUserFilter.Model.OnUserFilterListener {
    private ContractUserFilter.View view;
    private ContractUserFilter.Model model;

    public UserFilterPresenter(ContractUserFilter.View view){
        this.view = view;
        model = new UserFilterModel(this);
    }

    @Override
    public void userFilter(User user) {
        model.userFilterApi(user, this);
    }

    @Override
    public void onFinished(ArrayList<User> usersList) {
        view.successUserFilter(usersList);
    }

    @Override
    public void onFailure(String err) {

    }
}
