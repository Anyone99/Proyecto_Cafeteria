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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Usuario_ProductoAdaptador extends BaseAdapter {

    private Context context;
    private List<ProductoEntity> lProductos;
    private Button button_add;
    private HashMap<ProductoEntity, Integer> carrito;

    public Usuario_ProductoAdaptador(Context context, List<ProductoEntity> lProducto) {
        this.context = context;
        this.lProductos = lProducto;

    }

    @Override
    public int getCount() {
        return lProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return lProductos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_usuario_producto, null);

        ProductoEntity productoEntity = (ProductoEntity) getItem(position);


        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_usu_producto);
        TextView titleView = (TextView) convertView.findViewById(R.id.text_usu_producto_title);
        TextView descripcionView = (TextView) convertView.findViewById(R.id.text_usu_producto_descripcion);
        TextView precioView = (TextView) convertView.findViewById(R.id.text_usu_producto_precio);
        button_add = (Button) convertView.findViewById(R.id.button_usu_producto_add);


        imageView.setImageResource(productoEntity.getIdImagen());
        titleView.setText(productoEntity.getNombre());
        descripcionView.setText(productoEntity.getDescripcion());
        precioView.setText(String.valueOf(productoEntity.getPrecio()) + "€");


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductoEntity producto = lProductos.get(position);
                boolean existe = false;

                //si no existe el carrito se crea.
                if (carrito == null) {
                    carrito = new HashMap<ProductoEntity, Integer>();
                    carrito.put(producto, 1);
                } else {
                    //si existe ,comprobar el carrito si ha comprado mismo producto y con la cantidad
                    Iterator<ProductoEntity> iterator = carrito.keySet().iterator();
                    while (iterator.hasNext() && !existe) {
                        ProductoEntity key = iterator.next();
                        if (key.getIdProducto() == producto.getIdProducto()) {
                            existe = true;
                        } else {
                            existe = false;
                        }
                    }
                    if (existe) {
                        carrito.put(producto, carrito.get(producto) + 1);
                    } else {
                        carrito.put(producto, 1);
                    }

                }

                guardarPerferences(carrito);
                System.err.println("Carrito : " + carrito.get(producto) + " --> " + producto.toString());


            }
        });

        return convertView;
    }

    public void guardarPerferences(HashMap<ProductoEntity, Integer> carrito) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("carritos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> list_idProducto = new HashSet<>();
        Set<String> list_cantidad = new HashSet<>();


        Iterator<ProductoEntity> iterator = carrito.keySet().iterator();

        while (iterator.hasNext()) {
            ProductoEntity productoEntity = iterator.next();
            list_idProducto.add(String.valueOf(productoEntity.getIdProducto()));
            list_cantidad.add(String.valueOf(carrito.get(productoEntity)));
            System.err.println("Cantidad --> " + carrito.get(productoEntity));
        }

        editor.putStringSet("list_carrito_idProducto", list_idProducto);
        editor.putStringSet("list_carrito_cantidad", list_cantidad);
        editor.commit();

    }
}
