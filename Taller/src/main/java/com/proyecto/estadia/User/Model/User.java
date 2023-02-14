package com.proyecto.estadia.User.Model;


import com.proyecto.estadia.Car.Model.Car;
import com.proyecto.estadia.Rol.Model.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String surname;

    @Column (nullable = false)
    private String secondSurname;

    @Column (nullable = false)
    private String address;

    @Column (nullable = false)
    private String phone;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Car> car;

    public User() {
    }

    public User(long id, String name, String surname, String secondSurname, String address, String phone, String email, String password, Rol rol, List<Car> car) {
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

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getNombreCompleto(){
        return this.name != null && this.surname != null && this.secondSurname != null ?
                this.name + " " + this.surname + " " + this.secondSurname: "----";
    }
}
