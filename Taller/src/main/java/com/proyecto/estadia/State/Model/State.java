package com.proyecto.estadia.State.Model;


import com.proyecto.estadia.Car.Model.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "state")
    @JsonIgnore
    private List<Car> car;

    public State() {
    }

    public State(long id, String name, List<Car> car) {
        this.id = id;
        this.name = name;
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

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }
}
