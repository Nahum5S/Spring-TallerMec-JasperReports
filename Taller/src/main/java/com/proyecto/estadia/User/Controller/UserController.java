package com.proyecto.estadia.User.Controller;

import com.proyecto.estadia.Car.Controller.CarService;
import com.proyecto.estadia.User.Model.User;
import com.proyecto.estadia.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*"})
public class UserController {

    @Autowired
    UserService userService;
    CarService carService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable("id") long id){
        return userService.findById(id);
    }


    @PostMapping("/login")
    public ResponseEntity<Message> getByMailAndPassword(@RequestBody UserDTO userDTO) {
        return userService.findByEmailAndPassword(new User(
                userDTO.getEmail(),
                userDTO.getPassword()
        ));
    }

    @PostMapping("/")
    public ResponseEntity<Message> savedUser(@RequestBody UserDTO userDTO){
        return userService.save(new User(userDTO.getId(),userDTO.getName(),userDTO.getSurname(),userDTO.getSecondSurname(),userDTO.getAddress(),userDTO.getPhone() ,userDTO.getEmail(),
                userDTO.getPassword(),userDTO.getRol(),userDTO.getCar()));
    }

    @PutMapping("/")
    public ResponseEntity<Message> updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(new User(userDTO.getId(),userDTO.getName(),userDTO.getSurname(),userDTO.getSecondSurname(),userDTO.getAddress(),userDTO.getPhone() ,userDTO.getEmail(),
                userDTO.getPassword(),userDTO.getRol(),userDTO.getCar()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        userService.delete(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
