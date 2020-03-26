package com.example.proyecto_cafeteria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyecto_cafeteria.Entity.ListaPedidoEntity;
import com.example.proyecto_cafeteria.Entity.PedidoEntity;
import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.Entry.ListaPedido;
import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.R;

import java.util.List;

public class PedidoAdapter extends BaseAdapter {

    private Context context;
    private List<PedidoEntity> lPedido;

    public PedidoAdapter(Context context, List<PedidoEntity> lPedido) {
        this.context = context;
        this.lPedido = lPedido;
    }

    @Override
    public int getCount() {
        return lPedido.size();
    }

    @Override
    public Object getItem(int position) {
        return lPedido.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(context).inflate(R.layout.item_pedido, null);

        PedidoEntity pedidoEntity = lPedido.get(position);

        TextView idPedido = (TextView) convertView.findViewById(R.id.text_pedido_id_Pedido);
        TextView email = (TextView) convertView.findViewById(R.id.text_pedido_email);
        TextView precioTotal = (TextView) convertView.findViewById(R.id.text_pedido_precioTotal);
        TextView fecha = (TextView) convertView.findViewById(R.id.text_pedido_fecha);

        ListView productos = (ListView) convertView.findViewById(R.id.list_pedido_producto);

        UserEntity userEntity = User.findById(pedidoEntity.getUserEntity().getIdUsuario(), context);


        List<ListaPedidoEntity> listaPedido = ListaPedido.findByPedido(pedidoEntity.getIdPedido(), context);

        //Email de cliente;w
        email.setText(userEntity.getEmail());
        //id de Pedido, fecha de pedido
        idPedido.setText(String.valueOf(pedidoEntity.getIdPedido()));
        fecha.setText(String.valueOf(pedidoEntity.getFechaPedido()));

        //Calcular el precio.
        Float precio = calcular_precio_total(listaPedido);
        precioTotal.setText(String.valueOf(precio));

        ListPedidoAdapter listPedidoAdapter = new ListPedidoAdapter(context, listaPedido);
        productos.setAdapter(listPedidoAdapter);

        return convertView;
    }


    public Float calcular_precio_total(List<ListaPedidoEntity> list) {
        Float precio = 0f;
        for (int i = 0; i < list.size(); i++) {
            precio = precio + list.get(i).getCantidad() * list.get(i).getProductoEntity().getPrecio();
            System.out.println(precio);
        }
        return precio;
    }
}
