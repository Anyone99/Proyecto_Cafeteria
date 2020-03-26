package com.example.proyecto_cafeteria.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.SharedPreferences.*;

public class CarritoAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> listCantidad = new ArrayList<>();
    private List<ProductoEntity> listaProducto = new ArrayList<>();
    private Button button_eliminar;


    public CarritoAdapter(Context context, List<Integer> listCantidad, List<ProductoEntity> listaProducto) {
        this.listCantidad = listCantidad;
        this.listaProducto = listaProducto;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaProducto.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProducto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_carrito, null);

        ProductoEntity producto = listaProducto.get(position);

        int cantidad = listCantidad.get(position);

        TextView nombreProducto = convertView.findViewById(R.id.text_carrito_nombreProducto);
        TextView precio = convertView.findViewById(R.id.text_carrito_precio);
        TextView stock = convertView.findViewById(R.id.text_carrito_stock);
        TextView cantidadProducto = convertView.findViewById(R.id.text_carrito_unidades);
        ImageView imagenProducto = convertView.findViewById(R.id.imagen_carrito_producto);
        button_eliminar = (Button) convertView.findViewById(R.id.button_carrito_eliminar);


        nombreProducto.setText(producto.getNombre());
        cantidadProducto.setText(String.valueOf(cantidad));
        precio.setText(String.valueOf(producto.getPrecio()));
        stock.setText(String.valueOf(producto.getStock()));
        imagenProducto.setImageResource(producto.getIdImagen());

        //Eliminar el carrito
        button_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //eliminar los items
                listaProducto.remove(position);
                listCantidad.remove(position);

                //modificar
                SharedPreferences sharedPreferences = context.getSharedPreferences("carritos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("lista_carrito");

                Set<String> lista_product_cantidad = new HashSet<>();

                for (int i = 0; i < listaProducto.size(); i++) {
                    lista_product_cantidad.add(listaProducto.get(i).getIdProducto() + "-" + listCantidad.get(i));
                    System.out.println(i);
                }

                editor.putStringSet("lista_carrito", lista_product_cantidad);
                editor.apply();

                //notificar que ha cambiado
                notifyDataSetChanged();

            }
        });


        return convertView;

    }


}
