package com.proyecto.estadia.Rol.Controller;

import com.proyecto.estadia.Rol.Model.RolRepository;
import com.proyecto.estadia.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("OK",false,rolRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id){
        if(rolRepository.existsById(id)){
            return new ResponseEntity<>(new Message("Ok", false, rolRepository.findById(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El rol no existe", true, null), HttpStatus.BAD_REQUEST);
    }


}
