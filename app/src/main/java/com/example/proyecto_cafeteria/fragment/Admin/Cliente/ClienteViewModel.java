package com.example.proyecto_cafeteria.fragment.Admin.Cliente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClienteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClienteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Cliente");
    }

    public LiveData<String> getText() {
        return mText;
    }
}