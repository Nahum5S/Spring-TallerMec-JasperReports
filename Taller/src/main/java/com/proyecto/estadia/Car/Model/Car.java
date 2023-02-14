package com.proyecto.estadia.Car.Model;


import com.proyecto.estadia.State.Model.State;
import com.proyecto.estadia.User.Model.User;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String carPLates;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String descriptionDone;

    @Column
    private Double price;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "state_id")
    private State state;

    public Car() {
    }

    public  Car(long id, String carPLates, String brand, String model, String descriptionDone, Double price, User user, State state) {
        this.id = id;
        this.carPLates = carPLates;
        this.brand = brand;
        this.model = model;
        this.descriptionDone = descriptionDone;
        this.price = price;
        this.user = user;
        this.state = state;
    }

    public Car(long id, State state) {
        this.id = id;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarPLates() {
        return carPLates;
    }

    public void setCarPLates(String carPLates) {
        this.carPLates = carPLates;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescriptionDone() {
        return descriptionDone;
    }

    public void setDescriptionDone(String descriptionDone) {
        this.descriptionDone = descriptionDone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    public String getNombre(){
        return this.user != null ? this.user.getName() : "---";
    }

    public String getDescription(){
        return this.descriptionDone != null ? this.descriptionDone : "---";
    }




}
