package com.example.proyecto_cafeteria.fragment.User.Producto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto_cafeteria.R;

public class ProductoFragment extends Fragment {

    private ProductoViewModel productoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productoViewModel =
                ViewModelProviders.of(this).get(ProductoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_producto, container, false);
        final TextView textView = root.findViewById(R.id.text_producto);
        productoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}