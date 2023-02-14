package com.proyecto.estadia.Car.Controller;

import com.proyecto.estadia.State.Model.State;
import com.proyecto.estadia.User.Model.User;

public class CarDTO {

    private long id;

    private String carPLates;

    private String brand;

    private String model;

    private String descriptionDone;

    private Double price;

    private User user;

    private State state;

    public CarDTO() {
    }

    public CarDTO(long id, String carPLates, String brand, String model, String descriptionDone, Double price, User user, State state) {
        this.id = id;
        this.carPLates = carPLates;
        this.brand = brand;
        this.model = model;
        this.descriptionDone = descriptionDone;
        this.price = price;
        this.user = user;
        this.state = state;
    }

    public CarDTO(long id, State state) {
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
}
