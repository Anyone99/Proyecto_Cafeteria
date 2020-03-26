package com.example.proyecto_cafeteria.Entity;

import com.example.proyecto_cafeteria.Entry.Pedido;

public class PedidoEntity {

    private int idPedido;
    private UserEntity userEntity;
    private String FechaPedido;

    public PedidoEntity(int idPedido, UserEntity userEntity, String fechaPedido) {
        this.idPedido = idPedido;
        this.userEntity = userEntity;
        FechaPedido = fechaPedido;
    }

    public PedidoEntity(){

    }


    public int getIdPedido() {
        return idPedido;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public String getFechaPedido() {
        return FechaPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setFechaPedido(String fechaPedido) {
        FechaPedido = fechaPedido;
    }

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "idPedido=" + idPedido +
                ", userEntity=" + userEntity +
                ", FechaPedido='" + FechaPedido + '\'' +
                '}';
    }
}
