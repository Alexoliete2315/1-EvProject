package com.example.proy1bueno.loginUser.data;

import com.example.proy1bueno.beans.User;

import java.util.ArrayList;

public class DataUser {

    private String message;
    private ArrayList<User> usersList;


    public String getMessage() {
        return message;
    }
    public ArrayList<User> getLstUsers() {
        return usersList;
    }
    public void setLstUsers(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

}
