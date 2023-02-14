package com.proyecto.estadia.User.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository  <User, Long> {

    Optional<User> findByEmail (String email);

    Optional<User> findByEmailAndPassword (String email, String password);
    boolean existsByEmailAndPassword (String email, String password);

    List<User> deleteAllById (long id);





}
