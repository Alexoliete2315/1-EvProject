package com.example.proy1bueno.loginUser.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.proy1bueno.R;
import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.loginUser.ContractLoginUser;
import com.example.proy1bueno.loginUser.presenter.LoginUserPresenter;

public class LoginUser extends AppCompatActivity implements ContractLoginUser.View{
    SharedPreferences userPreferences;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;

    private LoginUserPresenter presenter =
            new LoginUserPresenter(this);

    /* PATRÃ“N SINGLETON*/
    private static LoginUser mainActivity = null;
    public static LoginUser getInstance(){
        return mainActivity; //0x457845AF
    }
    /* FIN PATRÃ“N SINGLETON*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        mainActivity = this;
        initComponents();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(LoginUser.this,
                    Manifest.permission.POST_NOTIFICATIONS)!=
                    PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(LoginUser.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }

    }
    private boolean LogCheck(){
        return userPreferences.getBoolean("LogCheck", false);
    }
    private void initComponents(){
        userPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            if ( !LogCheck() ){
                User user = new User();
                user.setUsername(edtEmail.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                presenter.login(user);
                Log.e("User To string","User: " + user);
                Log.e("logged success", "logged finished");
            }else{
                String datosUser = "";
                datosUser += "{Username: " + userPreferences.getString("username", "YO") + "},";
                datosUser += "idUser: " + userPreferences.getInt("idUser",0) + "}";
//                        Toast.makeText(this, DataUser, Toast.LENGTH_SHORT).show();
                Log.e("success", "LogCheck returned true");
                Intent intent = new Intent(this, Categories.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void successLogin(User user) {
        SharedPreferences.Editor editorPreferencias = userPreferences.edit();
        editorPreferencias.putBoolean("LogCheck", true);
        editorPreferencias.putString("username", user.getUsername());
        editorPreferencias.putInt("idUser", user.getIdUser());
        editorPreferencias.apply();
        Log.e("success", "editor añade user + id al preferences");

        //DEBERIA ENTRAR AQUI PARA PODER FILTRAR
//       Intent intent = new Intent(this, Categories.class);
        Intent intent = new Intent(this, Categories.class);
               startActivity(intent);
    }

    @Override
    public void failureLogin(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

}