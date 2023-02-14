package com.proyecto.estadia.Rol.Model;

import com.proyecto.estadia.User.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "rol")
    @JsonIgnore
    private List<User> user;

    public Rol() {
    }

    public Rol(long id, String name, List<User> user) {
        this.id = id;
        this.name = name;
        this.user = user;
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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
