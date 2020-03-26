package com.example.proyecto_cafeteria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.R;

import java.util.List;

public class ProductoAdaptador extends BaseAdapter {

    private Context context;
    private List<ProductoEntity> lProductos;

    public ProductoAdaptador(Context context, List<ProductoEntity> lProducto) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_producto, null);

        ProductoEntity productoEntity = (ProductoEntity) getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_producto);
        TextView titleView = (TextView) convertView.findViewById(R.id.text_producto_title);
        TextView descripcionView = (TextView) convertView.findViewById(R.id.text_producto_descripcion);
        TextView precioView = (TextView) convertView.findViewById(R.id.text_producto_precio);
        TextView idView = (TextView) convertView.findViewById(R.id.text_producto_id);

        idView.setText(String.valueOf(productoEntity.getIdProducto()));

        imageView.setImageResource(productoEntity.getIdImagen());

        titleView.setText(productoEntity.getNombre());
        descripcionView.setText(productoEntity.getDescripcion());
        precioView.setText(String.valueOf(productoEntity.getPrecio()) + "€");

        notifyDataSetChanged();

        return convertView;
    }
}
