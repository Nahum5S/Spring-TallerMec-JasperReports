package com.proyecto.estadia.Car.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository <Car, Long> {

    Optional<Car> findByCarPLates(String carPlates);

    @Query("SELECT C FROM Car C WHERE C.id=:id AND C.user.id=:idUser")
    Iterable<Car> findById(long id, long idUser);
    List<Car> findByUser_Id(long id);

    List<Car> deleteAllByUserId(long id);

    @Query("SELECT P FROM Car P WHERE P.id=:id AND P.user.id=:idUser")
    Optional<Car> findByIdAndUserId(long idUser, long id);

    @Query(value = "SELECT price FROM Car WHERE user_id =:idUser AND id=:id", nativeQuery = true)
    Double totalByIdCar(long idUser, long id);

}
