package com.example.proy1bueno.userFilter.data;

import com.example.proy1bueno.beans.User;

import java.util.ArrayList;

public class DataUserFilter {
    private String message;
    private ArrayList<User> usersList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }
}
