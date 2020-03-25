package com.example.proyecto_cafeteria.Entity;

public class ProductoEntity {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private float precio;
    private int stock;
    private int idImagen;

    public ProductoEntity(int idProducto, String nombre, String descripcion, float precio, int stock, int idImagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idImagen = idImagen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdImagen() {
        return idImagen;
    }
    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    @Override
    public String toString() {
        return "ProductoEntity{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
