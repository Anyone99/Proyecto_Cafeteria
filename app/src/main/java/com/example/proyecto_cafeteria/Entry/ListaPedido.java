package com.example.proyecto_cafeteria.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_cafeteria.Entity.ListaPedidoEntity;
import com.example.proyecto_cafeteria.Entity.PedidoEntity;
import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

public class ListaPedido {

    public static final String NOMBRE_TABLA = "ListaPedidos";
    public static final String ID_LISTA = "idListaPedido";
    public static final String ID_PEDIDO = "idPedido";
    public static final String ID_PRODUCTO = "idProducto";
    public static final String CANTIDAD_PEDIDO = "cantidad";

    //public ListaPedido(int idLista, ProductoEntity productoEntity, int cantidad, PedidoEntity pedidoEntity) {

    // private static final String CREATE_LISTAPEDIDO = "CREATE TABLE IF NOT EXISTS " + ListaPedido.NOMBRE_TABLA + " ("
    //            + ListaPedido.ID_LISTA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    //            + ListaPedido.ID_PEDIDO + " INTEGER, "
    //            + ListaPedido.ID_PRODUCTO + " INTEGER, "
    //            + ListaPedido.CANTIDAD_PEDIDO + " INTEGER, "

    public static List<ListaPedidoEntity> findByPedido(int idPedido, Context context) {
        List<ListaPedidoEntity> listaPedidos = new ArrayList<>();
        ListaPedidoEntity pedido = new ListaPedidoEntity();
        PedidoEntity pedidoEntity = new PedidoEntity();

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

        String[] args = new String[]{String.valueOf(idPedido)};

        Cursor cursor = db.rawQuery("Select * from " + NOMBRE_TABLA + " WHERE idPedido = ? ", args);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                pedidoEntity = Pedido.findById(cursor.getInt(1), context);
                ProductoEntity productoEntity = Producto.findById(cursor.getInt(2), context);
                pedido = new ListaPedidoEntity(cursor.getInt(0), productoEntity, cursor.getInt(3), pedidoEntity);
                listaPedidos.add(pedido);
            }
        }
        cursor.close();
        return listaPedidos;
    }

    public static long save(ContentValues contentValues, Context context){

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        long idListaPedido = db.insert(ListaPedido.NOMBRE_TABLA, null, contentValues);

        if (idListaPedido > 0 ){
            return  idListaPedido;
        }else{
            return 0;
        }

    }
}
