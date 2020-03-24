package com.example.proyecto_cafeteria.fragment.Admin.Producto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto_cafeteria.R;

import java.util.List;

public class ProductoFragment extends Fragment {

    private Button button_delete, button_modify, button_add;
    private EditText edit_buscar;
    private TextView text_producto;
    private ListView listView_producto;
    private CardView cardView;


    private ProductoViewModel productoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productoViewModel =
                ViewModelProviders.of(this).get(ProductoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_producto, container, false);
        final TextView textView = root.findViewById(R.id.text_admin_producto);
        productoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("onActivityCreated******************************");
        init();
    }

    public void init(){
        cardView = getActivity().findViewById(R.id.cardView_producto);
        listView_producto = getActivity().findViewById(R.id.listView_producto);
        button_delete = getActivity().findViewById(R.id.button_producto_delete);
        button_add =getActivity().findViewById(R.id.button_producto_add);
        button_modify = getActivity().findViewById(R.id.button_producto_modify);
        edit_buscar = getActivity().findViewById(R.id.edit_producto_nombre);



    }


}