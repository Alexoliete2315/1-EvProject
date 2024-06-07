package com.example.proy1bueno.userFilter;


import com.example.proy1bueno.beans.User;

import java.util.ArrayList;

public interface ContractUserFilter {

    public interface Presenter{
        void userFilter(User user);
    }

    public interface Model{
        void userFilterApi(User user,
                          ContractUserFilter.Model.OnUserFilterListener onUserFilterListener);

        interface OnUserFilterListener {
            void onFinished(ArrayList<User> usersList);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successUserFilter(ArrayList<User> usersList);
        void failureUserFilter(String err);
    }

}
