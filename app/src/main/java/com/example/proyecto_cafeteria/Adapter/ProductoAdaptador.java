package com.example.proyecto_cafeteria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.proyecto_cafeteria.Entity.ProductoEntity;

import java.util.List;

public class ProductoAdaptador extends BaseAdapter {

    private Context context;
    private List<ProductoEntity> lProductos;
    private int idImagen, idNombre, idDescripcion, idPrecio, idProducto,idResource;

    public ProductoAdaptador(Context context, int idResource, List<ProductoEntity> lProducto,int idImagen, int idNombre, int idDescripcion, int idPrecio, int idProducto) {
        this.context = context;
        this.idResource = idResource;
        this.lProductos = lProducto;
        this.idImagen = idImagen;
        this.idDescripcion = idDescripcion;
        this.idNombre = idNombre;
        this.idPrecio = idPrecio;
        this.idProducto = idProducto;
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

        convertView = LayoutInflater.from(context).inflate(idResource,null);

        ProductoEntity productoEntity = (ProductoEntity) getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(idImagen);
        TextView titleView = (TextView) convertView.findViewById(idNombre);
        TextView descripcionView = (TextView) convertView.findViewById(idDescripcion);
        TextView precioView = (TextView) convertView.findViewById(idPrecio);

        if (idProducto>0){
            TextView idView = (TextView) convertView.findViewById(idProducto);
            idView.setText(String.valueOf(productoEntity.getIdProducto()));

        }

        imageView.setImageResource(productoEntity.getIdImagen());

        titleView.setText(productoEntity.getNombre());
        descripcionView.setText(productoEntity.getDescripcion());
        precioView.setText(String.valueOf(productoEntity.getPrecio()) + "€");

        return convertView;
    }
}
