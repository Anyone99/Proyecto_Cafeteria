package com.example.proyecto_cafeteria.fragment.User.Producto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProductoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is producto fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}