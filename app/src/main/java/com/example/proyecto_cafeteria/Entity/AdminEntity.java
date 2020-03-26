package com.example.proyecto_cafeteria.Entity;

public class AdminEntity {

    private int idAdmin;
    private String email;
    private String password;

    public AdminEntity(int idAdmin, String email, String password) {
        this.idAdmin = idAdmin;
        this.email = email;
        this.password = password;
    }

    public  AdminEntity(){

    }
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "idAdmin=" + idAdmin +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
