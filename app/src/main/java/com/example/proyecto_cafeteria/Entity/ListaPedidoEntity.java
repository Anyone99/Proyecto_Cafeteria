package com.example.proyecto_cafeteria.Entity;

public class ListaPedidoEntity {
    private int idLista;
    private ProductoEntity productoEntity;
    private int cantidad;
    private PedidoEntity pedidoEntity;

    public ListaPedidoEntity(int idLista, ProductoEntity productoEntity, int cantidad, PedidoEntity pedidoEntity) {
        this.idLista = idLista;
        this.productoEntity = productoEntity;
        this.cantidad = cantidad;
        this.pedidoEntity = pedidoEntity;
    }

    public ListaPedidoEntity() {


    }


    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public PedidoEntity getPedidoEntity() {
        return pedidoEntity;
    }

    public void setPedidoEntity(PedidoEntity pedidoEntity) {
        this.pedidoEntity = pedidoEntity;
    }

    @Override
    public String toString() {
        return "ListaPedido{" +
                "idLista=" + idLista +
                ", productoEntity=" + productoEntity +
                ", cantidad=" + cantidad +
                ", pedidoEntity=" + pedidoEntity +
                '}';
    }
}
