package com.example.proyecto_cafeteria.Entry;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

public class User {
    public static final String TABLE_NAME = "Usuario";
    public static final String ID_USUARIO = "idUsuario";
    public static final String EMAIL = "Email";
    public static final String NOMBRE = "Nombre";
    public static final String PASSWORD = "password";
    public static final String APELLIDO = "Apellido";
    public static final String TELEFONO = "telefono";
    public static final String FNACIMIENTO = "fnacimiento";

    /*   private static final String CREATE_USUARIO = "CREATE TABLE IF NOT EXISTS " + User.TABLE_NAME
            + " ( " + User.ID_USUARIO + " Integer Primary Key AUTOINCREMENT, "
            + User.NOMBRE + " Text Not Null, "
            + User.APELLIDO + " Text Not Null , "
            + User.EMAIL + " Text Not Null Unique, "
            + User.PASSWORD + " Text Not Null, "
            + User.TELEFONO + " Integer Not Null, "
            + User.FNACIMIENTO + " Text Not Null)";*/

    /*  public UserEntity(int idUsuario, String nombre, String apellido, String fnacimiento, int telefono, String email, String password) {*/

    public static UserEntity findById(int id, Context context) {
        UserEntity userEntity = new UserEntity();
        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

        String[] args = new String[]{String.valueOf(id)};
        Cursor cursor = db.rawQuery("Select * From " + TABLE_NAME + " Where " + ID_USUARIO + " = ? ", args);

        if (cursor != null) {
            cursor.moveToFirst();
            userEntity = new UserEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(6), cursor.getInt(5), cursor.getString(3), cursor.getString(4));
        }
        cursor.close();
        return userEntity;

    }

    public static UserEntity findByEmail(String email, Context context) {
        UserEntity userEntity = new UserEntity();
        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

        String[] args = new String[]{email};
        Cursor cursor = db.rawQuery("Select * From " + TABLE_NAME + " Where " + EMAIL + " = ? ", args);

        if (cursor != null) {
            cursor.moveToFirst();
            userEntity = new UserEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(6), cursor.getInt(5), cursor.getString(3), cursor.getString(4));
        }
        return userEntity;
    }

    public static UserEntity findByEmailPassword(String email, String password, Context context) {
        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        UserEntity userEntity = new UserEntity();

        String[] args = new String[]{email, password};
        Cursor cursor = db.rawQuery("Select * From " + User.TABLE_NAME + " Where " + User.EMAIL + " = ? AND " + User.PASSWORD + " = ? ", args);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userEntity = new UserEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(6), cursor.getInt(5), cursor.getString(3), cursor.getString(4));
                if (userEntity != null) {
                    return userEntity;
                }
            }

        }
        cursor.close();

        return null;

    }

    public static long save(ContentValues contentValues, Context context) {
        SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = SQLiteDBHelper.getWritableDatabase();

        long idCliente = db.insert(User.TABLE_NAME, null, contentValues);
        if (idCliente > 0) {

            return idCliente;
        }
        db.close();

        return 0;


    }


}
