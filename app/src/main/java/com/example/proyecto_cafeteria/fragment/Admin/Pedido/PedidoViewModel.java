package com.example.proyecto_cafeteria.fragment.Admin.Pedido;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PedidoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PedidoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Pedido");
    }

    public LiveData<String> getText() {
        return mText;
    }
}