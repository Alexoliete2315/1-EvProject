package com.example.proy1bueno.userFilter.model;


import android.util.Log;

import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.loginUser.data.DataUser;
import com.example.proy1bueno.userFilter.ContractUserFilter;
import com.example.proy1bueno.userFilter.data.DataUserFilter;
import com.example.proy1bueno.userFilter.presenter.UserFilterPresenter;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFilterModel  implements ContractUserFilter.Model{
    private static final String IP_BASE = "192.168.104.76:8080";
    //    private static final String IP_BASE = "192.168.1.196:8080";
    private UserFilterPresenter presenter;

    public UserFilterModel(UserFilterPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void userFilterApi(User user, OnUserFilterListener onUserFilterListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
//                  http:// 192.168.1.132:8088/untitled/

        Call<DataUserFilter> call;
        call = apiService.getUserFilter("USER.FILTER","userMostSells");
        call.enqueue(new Callback<DataUserFilter>() {
            @Override
            public void onResponse(Call<DataUserFilter> call, Response<DataUserFilter> response) {
                if (response.isSuccessful()){
                    Log.e("On Response","isSuccesfull");
                    DataUserFilter dataUserFilter = response.body();
                    Log.e("DATA FILTER","QUE OSTIAS HAY AQUI?" + dataUserFilter.getUsersList());
                    ArrayList<User> usersList = dataUserFilter.getUsersList();
                    Log.e("Response Success","USERLIST " + usersList.toString());
                    onUserFilterListener.onFinished(usersList);
                }else{
                    Log.e("Response Error", "ERROR HTTP" + response.code());
                    try{
                        String errorBody = response.errorBody().string();
                        Log.e("Response error", "ERROR BODY " + errorBody);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataUserFilter> call, Throwable t) {
                Log.e("ON FAILURE", "DENTRO RESPONSE IS FAILURE" + t.getMessage());
            }
        });


    }
}
