package com.example.proyecto_cafeteria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.proyecto_cafeteria.Entity.ListaPedidoEntity;
import com.example.proyecto_cafeteria.Entry.ListaPedido;
import com.example.proyecto_cafeteria.R;

import java.util.List;

public class ListPedidoAdapter extends BaseAdapter {
    private Context context;
    private List<ListaPedidoEntity> listaPedidos;

    public ListPedidoAdapter(Context context, List<ListaPedidoEntity> listaPedidos) {
        this.context = context;
        this.listaPedidos = listaPedidos;
    }

    @Override
    public int getCount() {
        return listaPedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(context).inflate(R.layout.item_pedido_producto, null);

        ListaPedidoEntity listaPedidoEntity = listaPedidos.get(position);

        TextView nombre = (TextView) convertView.findViewById(R.id.text_pedido_producto_nombre);
        TextView precio = (TextView) convertView.findViewById(R.id.text_pedido_producto_precio);
        TextView cantidad = (TextView) convertView.findViewById(R.id.text_pedido_producto_cantidad);

        nombre.setText(listaPedidoEntity.getProductoEntity().getNombre());
        precio.setText(String.valueOf(listaPedidoEntity.getProductoEntity().getPrecio()));
        cantidad.setText(String.valueOf(listaPedidoEntity.getCantidad()));

        notifyDataSetChanged();
        return convertView;
    }
}
