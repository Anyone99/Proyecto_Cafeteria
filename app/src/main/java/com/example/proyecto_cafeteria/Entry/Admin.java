package com.example.proyecto_cafeteria.Entry;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_cafeteria.Entity.AdminEntity;
import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

public class Admin {
    public static final String TABLE_NAME = "Admin";
    public static final String ID_ADMIN = "idAdmin";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String NOMBRE = "nombre";


    public static AdminEntity findByEmailPassword(String email, String password, Context context) {
        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        AdminEntity adminEntity = new AdminEntity();

        String[] args = new String[]{email, password};

        Cursor cursor = db.rawQuery("Select * From " + Admin.TABLE_NAME + " Where " + Admin.USUARIO + " = ? AND " + Admin.PASSWORD + " = ? ", args);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                adminEntity = new AdminEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                return adminEntity;
            }
        }
        cursor.close();

        return null;


    }


}
