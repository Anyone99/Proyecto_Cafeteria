package com.example.proyecto_cafeteria.Entity;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class UserEntity extends ArrayList<String> {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String fnacimiento;
    private int telefono;
    private String email;

    private String password;

    public UserEntity(int idUsuario, String nombre, String apellido, String fnacimiento, int telefono, String email, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.apellido = apellido;
        this.fnacimiento = fnacimiento;
    }

    public UserEntity(){

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "idUsuario : " + idUsuario + "\n" +
                "Nombre : " + nombre + " , "+ apellido + "\n" +
                "Fecha Nacimiento : " + fnacimiento + "\n" +
                "Teléfono : " + telefono +"\n"+
                "Email : " + email + "\n" +
                "Password : " + password;
    }


}
