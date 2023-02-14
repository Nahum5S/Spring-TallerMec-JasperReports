package com.proyecto.estadia.Rol.Controller;

import com.proyecto.estadia.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = {"*"})
public class RolController {

    @Autowired

    RolService rolService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return rolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable("id") long id){
        return rolService.findById(id);
    }

}
