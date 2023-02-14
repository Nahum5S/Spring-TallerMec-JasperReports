package com.proyecto.estadia.User.Controller;

import com.proyecto.estadia.Car.Model.Car;
import com.proyecto.estadia.Rol.Model.Rol;

import java.util.List;

public class UserDTO {

    private long id;
    private String name;
    private String surname;
    private String secondSurname;
    private String address;
    private String phone;
    private String email;
    private String password;
    private Rol rol;

    private List<Car> car;

    public UserDTO() {
    }

    public UserDTO(long id, String name, String surname, String secondSurname, String address, String phone, String email, String password, Rol rol, List<Car> car) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.car = car;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }
}
