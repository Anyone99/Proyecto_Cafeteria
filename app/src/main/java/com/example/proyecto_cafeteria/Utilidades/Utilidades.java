package com.example.proyecto_cafeteria.Utilidades;

import android.os.ParcelFormatException;

import java.util.regex.Pattern;

public class Utilidades {

    public static boolean isNumero(String contenido) {
        try {
            Integer.parseInt(contenido);
            return true;
        } catch (NumberFormatException p) {
            return false;
        }
    }


}
