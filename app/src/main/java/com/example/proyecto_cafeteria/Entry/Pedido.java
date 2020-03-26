package com.example.proyecto_cafeteria.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_cafeteria.Entity.PedidoEntity;
import com.example.proyecto_cafeteria.Entity.PedidoEntity;
import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    public static final String NOMBRE_TABLA = "Pedidos";
    public static final String ID_PEDIDO = "idPedido";
    public static final String ID_USUARIO = "idUsuario";
    public static final String FECHA_PEDIDO = "FechaPedido";


    public static List<PedidoEntity> getAll(Context context) {

        PedidoEntity pedidoEntity = new PedidoEntity();
        List<PedidoEntity> list = new ArrayList<>();

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from " + NOMBRE_TABLA, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setIdUsuario(cursor.getInt(1));
                pedidoEntity = new PedidoEntity(cursor.getInt(0), userEntity, cursor.getString(2));

                list.add(pedidoEntity);

            }
        }
        cursor.close();
        return list;

    }

    //    public PedidoEntity(int idPedido, UserEntity userEntity, String fechaPedido) {

    //private static final String CREATE_PEDIDO = "CREATE TABLE IF NOT EXISTS " + Pedido.NOMBRE_TABLA + " ("
    //            + Pedido.ID_PEDIDO + " Integer Primary Key AUTOINCREMENT,"
    //            + Pedido.ID_USUARIO + " Integer,"
    //            + Pedido.FECHA_PEDIDO + " Text not null,"
    //            + "FOREIGN KEY (" + Pedido.ID_USUARIO + ") REFERENCES " + User.TABLE_NAME + " ( " + User.ID_USUARIO + "))";

    public static PedidoEntity findById(int idPedido, Context context) {
        PedidoEntity pedidoEntity = new PedidoEntity();

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        String[] args = new String[]{String.valueOf(idPedido)};
        Cursor cursor = db.rawQuery("Select * from " + NOMBRE_TABLA + " Where " + ID_PEDIDO + " = ? ", args);

        if (cursor != null) {
            cursor.moveToFirst();
            UserEntity userEntity = User.findById(cursor.getInt(1), context);
            pedidoEntity = new PedidoEntity(cursor.getInt(0), userEntity, cursor.getString(2));
        }
        cursor.close();
        return pedidoEntity;
    }

    public static List<PedidoEntity> findByUser(UserEntity userEntity, Context context) {

        List<PedidoEntity> list = new ArrayList<>();

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        String[] args = new String[]{String.valueOf(userEntity.getIdUsuario())};
        Cursor cursor = db.rawQuery("Select * from " + NOMBRE_TABLA + " Where " + ID_USUARIO + " = ? ", args);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                PedidoEntity pedidoEntity = new PedidoEntity(cursor.getInt(0), userEntity, cursor.getString(2));
                list.add(pedidoEntity);
            }
        }
        cursor.close();
        return list;
    }

    public static long save(ContentValues contentValues, Context context) {
        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        long idPedido = db.insert(Pedido.NOMBRE_TABLA, null, contentValues);

        if (idPedido > 0) {
            return idPedido;
        } else {
            return 0;
        }
    }
}
