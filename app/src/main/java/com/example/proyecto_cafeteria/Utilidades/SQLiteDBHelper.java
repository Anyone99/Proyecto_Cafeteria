package com.example.proyecto_cafeteria.Utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.proyecto_cafeteria.Entry.Admin;
import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.Entry.User;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Cafeteria";

    private static final String CREATE_USUARIO = "CREATE TABLE IF NOT EXISTS " + User.TABLE_NAME
            + " ( " + User.ID_USUARIO + " Integer Primary Key AUTOINCREMENT, "
                   + User.NOMBRE + " Text Not Null, "
                   + User.APELLIDO + " Text Not Null , "
                   + User.EMAIL + " Text Not Null Unique, "
                   + User.PASSWORD + " Text Not Null, "
                   + User.TELEFONO + " Integer Not Null, "
                   + User.FNACIMIENTO + " Text Not Null)";

    private static final String CREATE_ADMIN = "CREATE TABLE IF NOT EXISTS " + Admin.TABLE_NAME + " ("
            + Admin.ID_ADMIN + " Integer Primary Key AUTOINCREMENT, "
            + Admin.NOMBRE + " Text Not Null, "
            + Admin.USUARIO + " Text Not Null Unique, "
            + Admin.PASSWORD + " Text Not Null )";

    private static final String CREATE_PRODUCTO = "CREATE TABLE IF NOT EXISTS " + Producto.TABLE_NAME + " ("
            + Producto.ID_PRODUCTO + " Integer PRIMARY KEY AUTOINCREMENT,"
            + Producto.NOMBRE_PRODUCTO + " TEXT NOT NULL, "
            + Producto.PRECIO_PRODUCTO + " FLOAT NOT NULL, "
            + Producto.STOCK_PRODUCTO + " INTEGER NOT NULL)";

    private static final String INSERT_ADMIN = "INSERT INTO " + Admin.TABLE_NAME + " ( " + Admin.NOMBRE + ", " + Admin.USUARIO + ", " + Admin.PASSWORD + ") VALUES ('Administrador', 'root@root.com', 'root');";
    private static final String DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS " + User.TABLE_NAME;
    private static final String DROP_TABLE_ADMIN = "DROP TABLE IF EXISTS " + Admin.TABLE_NAME;
    private static final String DROP_TABLE_PRODUCTO = "DROP TABLE IF EXISTS " + Producto.TABLE_NAME;

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MainActivity", "Creado Database ！");
        db.execSQL(CREATE_USUARIO);
        db.execSQL(CREATE_ADMIN);
        db.execSQL(CREATE_PRODUCTO);
        db.execSQL(INSERT_ADMIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MainActivity", "Actualizado Database ！");
        db.execSQL(DROP_TABLE_USUARIO);
        db.execSQL(DROP_TABLE_ADMIN);
        db.execSQL(DROP_TABLE_PRODUCTO);
        onCreate(db);
    }
}
