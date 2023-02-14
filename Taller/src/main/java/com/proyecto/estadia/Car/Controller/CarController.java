package com.proyecto.estadia.Car.Controller;

import com.proyecto.estadia.Car.Model.Car;
import com.proyecto.estadia.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = {"*"})
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return carService.findAll();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Message> getByIdCar(@PathVariable("id") long id){
        return carService.findById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable("id") long id){
        return carService.findByUser_Id(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> saveCar(@RequestBody CarDTO carDTO){
        return carService.save(new Car
                (carDTO.getId(),
                carDTO.getCarPLates(),
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getDescriptionDone(),
                carDTO.getPrice(),
                carDTO.getUser(),
                carDTO.getState()));
    }

    @PutMapping("/")
    public ResponseEntity<Message> updateCar(@RequestBody CarDTO carDTO){
        return carService.updateCar(new Car
                (carDTO.getId(),
                        carDTO.getCarPLates(),
                        carDTO.getBrand(),
                        carDTO.getModel(),
                        carDTO.getDescriptionDone(),
                        carDTO.getPrice(),
                        carDTO.getUser(),
                        carDTO.getState()));
    }

    @PostMapping("/state")
    public ResponseEntity<Message> updateStateCar(@RequestBody CarDTO carDTO){
        return carService.updateCar(new Car
                (carDTO.getId(),carDTO.getState()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        carService.delete(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/exportInvoice/{idUser},{id}")
    public ResponseEntity<Resource> exportInvoice(@PathVariable("idUser") long idUser, @PathVariable("id") long id){
        return this.carService.exportInvoice(idUser, id);
    }
}
