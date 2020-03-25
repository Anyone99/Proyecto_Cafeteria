package com.example.proyecto_cafeteria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.R;

import java.util.HashMap;
import java.util.Iterator;

public class CarritoAdapter extends BaseAdapter {

    private HashMap<ProductoEntity, Integer> carrito;
    private Context context;

    public CarritoAdapter(Context context, HashMap<ProductoEntity, Integer> carrito) {
        this.carrito = carrito;
        this.context = context;
    }

    @Override
    public int getCount() {
        return carrito.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_usuario_producto, null);

        TextView nombreProducto = convertView.findViewById(R.id.text_carrito_nombreProducto);
        TextView precio = convertView.findViewById(R.id.text_carrito_precio);
        TextView stock = convertView.findViewById(R.id.text_carrito_stock);
        TextView cantidad = convertView.findViewById(R.id.text_carrito_unidades);
        ImageView imagenProducto = convertView.findViewById(R.id.imagen_carrito_producto);




        return convertView;
    }


}
