package com.example.proyecto_cafeteria.fragment.User.Producto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.Main.Carrito;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;
import com.example.proyecto_cafeteria.Adapter.Usuario_ProductoAdaptador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductoFragment extends Fragment {

    private ProductoViewModel productoViewModel;
    private Button button_añadir;
    private FloatingActionButton button_carrito;
    private List<ProductoEntity> listProducto = new ArrayList<>();
    private ListView listView_producto;
    private Usuario_ProductoAdaptador productoAdaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productoViewModel =
                ViewModelProviders.of(this).get(ProductoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_producto, container, false);
        final TextView textView = root.findViewById(R.id.text_producto_title);
        productoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("onActivityCreated******************************");
        init();
    }

    public void init() {

        button_añadir = (Button) getActivity().findViewById(R.id.button_usu_producto_add);
        button_carrito = (FloatingActionButton) getActivity().findViewById(R.id.actionButton_usuario_carrito);
        listView_producto = (ListView) getActivity().findViewById(R.id.listView_usu_producto);


        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(getContext());
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from " + Producto.TABLE_NAME, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ProductoEntity producto = new ProductoEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getInt(4), R.mipmap.cafeicon);
                listProducto.add(producto);
            }

            productoAdaptador = new Usuario_ProductoAdaptador(getContext(), listProducto);
            listView_producto.setAdapter(productoAdaptador);
        }

        button_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Carrito.class);
                startActivity(intent);
            }
        });


    }
}