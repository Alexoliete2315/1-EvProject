package com.example.proy1bueno.loginUser.model;


import android.util.Log;

import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.loginUser.ContractLoginUser;
import com.example.proy1bueno.loginUser.data.DataUser;
import com.example.proy1bueno.loginUser.presenter.LoginUserPresenter;
import com.example.proy1bueno.utils.ApiService;
import com.example.proy1bueno.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModel implements ContractLoginUser.Model {

    private static final String IP_BASE = "192.168.104.76:8080";
    private LoginUserPresenter presenter;
    public LoginUserModel(LoginUserPresenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void loginAPI(User user, final ContractLoginUser.Model.OnLoginUserListener onLoginUserListener) {
        // Crear una instancia de ApiService
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").
                create(ApiService.class);
        Log.e("Apiservice","Apiservice" + apiService);

// Realizar la solicitud al Servlet
        // Call<MyData> call = apiService.getMyData("1");
        Call<DataUser> call = apiService.getDataLoginUser("USER.LOGIN", user.getUsername(), user.getPassword());
        Log.e("call","call" + call);

        call.enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(Call<DataUser> call, Response<DataUser> response) {
                if (response.isSuccessful()) {
                    Log.e("Response", "Response: " + response);
                    DataUser dataUser = response.body();
                    ArrayList<User> usersList = dataUser.getLstUsers();
                    Log.e("LSTUSERS","LST USERS" +  usersList);
                    Log.e("dataUser","dataUser" +  dataUser);


                    try {

                        onLoginUserListener.onFinished(usersList.get(0));
                        Log.e("LOGIN CORRECRO ONRESPONSE","ESTOY LOGUEADO");

                    }catch(IndexOutOfBoundsException e){
                        Log.e("No Found User","no user exists");
                    }
                } else {
                    Log.e("Response Error", "CÃ³digo de estado HTTP: " + response.code());
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("Response Error", "Cuerpo de error: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataUser> call, Throwable t) {
                // Manejar errores de red o del servidor
                Log.e("Response Error", "Cuerpo de error: " + t.getMessage());
            }
        });
    }

}
