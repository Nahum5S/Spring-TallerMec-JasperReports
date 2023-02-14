package com.proyecto.estadia.User.Controller;

import com.proyecto.estadia.User.Model.User;
import com.proyecto.estadia.User.Model.UserRepository;
import com.proyecto.estadia.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("ok", false, userRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id){
        if(userRepository.existsById(id)){
            return new ResponseEntity<>(new Message("Encontrado", false, userRepository.findById(id)), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("No encontrado", true, null), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findByEmailAndPassword(User user){
        if(userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword())){
            return new ResponseEntity<>(new Message("IniciandoSesion", false, userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())), HttpStatus.OK);
        }else{
                return new ResponseEntity<>(new Message("NoEncontrado", true, null), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(User user) {
        Optional<User> exist = userRepository.findByEmail(user.getEmail());
        if (exist.isPresent()) {
            return new ResponseEntity<>(new Message("El usuario ya existe", true, null), HttpStatus.BAD_REQUEST);
        }
        User savedUser = userRepository.saveAndFlush(user);
        return new ResponseEntity<>(new Message("Usuario registrado correctamente", false, savedUser), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return new ResponseEntity<>(new Message("ActualizadoCorrectamenteCli", false, userRepository.saveAndFlush(user)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("No se encontro", true, null), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void delete(long id){
        userRepository.deleteAllById(id);
    }




}
