package com.example.proy1bueno.addCompra;

import com.example.proy1bueno.beans.Compra;


public interface ContractAddCompra {
    public interface Presenter{
        void addCompra(Compra compra);
    }

    public interface Model{
        void addCompraApi(Compra compra,
                            OnAddCompraListener onAddCompraListener);

        interface OnAddCompraListener{
            void onFinished(Compra compra);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successAddCompra(Compra compra);
        void failureAddCompra(String err);
    }
}
