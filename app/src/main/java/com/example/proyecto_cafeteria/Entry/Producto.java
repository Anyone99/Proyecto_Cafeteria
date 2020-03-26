package com.example.proyecto_cafeteria.Entry;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

public class Producto {
    public static final String TABLE_NAME = "Producto";
    public static final String ID_PRODUCTO = "idProducto";
    public static final String NOMBRE_PRODUCTO = "nombreProducto";
    public static final String DESCRIPCION_PRODUCTO = "Descripcion";
    public static final String PRECIO_PRODUCTO = "precio";
    public static final String STOCK_PRODUCTO = "stock";


//    public ProductoEntity(int idProducto, String nombre, String descripcion, float precio, int stock, int idImagen) {

//  private static final String CREATE_PRODUCTO = "CREATE TABLE IF NOT EXISTS " + Producto.TABLE_NAME + " ("
//            + Producto.ID_PRODUCTO + " Integer PRIMARY KEY AUTOINCREMENT,"
//            + Producto.NOMBRE_PRODUCTO + " TEXT NOT NULL, "
//            + Producto.DESCRIPCION_PRODUCTO + " TEXT NOT NULL, "
//            + Producto.PRECIO_PRODUCTO + " FLOAT NOT NULL, "
//            + Producto.STOCK_PRODUCTO + " INTEGER NOT NULL)";


    public static ProductoEntity findById(int idProducto, Context context) {
        ProductoEntity productoEntity = new ProductoEntity();

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        String[] args = new String[]{String.valueOf(idProducto)};
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " Where " + ID_PRODUCTO + " = ? ", args);

        if (cursor != null) {
            cursor.moveToFirst();
            productoEntity = new ProductoEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getInt(4), R.mipmap.cafeicon);
        }
        return productoEntity;
    }
}