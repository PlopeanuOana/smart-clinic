package com.asignment3.asignment3.controller;

import com.asignment3.asignment3.model.dto.PatientDTO;
import com.asignment3.asignment3.model.dto.UserDTO;
import com.asignment3.asignment3.model.enumeration.UserRole;
import com.asignment3.asignment3.service.PatientService;
import com.asignment3.asignment3.service.UserService;
import com.asignment3.asignment3.validare.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( value = "/user")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userValidator = userValidator;
        this.userService = userService;

    }

    @GetMapping(value ="/all")
    public List<UserDTO> printUsers() {
        return userService.findAllUsersDTO();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
        userValidator.validateUser(userDTO, result);

        if(result.hasErrors()) {
            return new ResponseEntity<Object>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UserDTO save = userService.register(userDTO);
        return ResponseEntity.ok(save);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid UserDTO userDTO, BindingResult result){
        userValidator.validateUser(userDTO,result);
        if(result.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

        UserDTO save = userService.save(userDTO);
        return ResponseEntity.ok(save);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete( @RequestParam Long id) {
        //da eroare ca e legat si de bank si de patient sau doctor, asa ca stergi un doctor sau un pacient
        //acolo verifici daca are si cont bancar si il stergi si pe ala
        UserDTO user = userService.findById(id);
        if(user.getRole().equals(UserRole.ADMIN))
            userService.deleteUser(id);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> logIn(@RequestBody UserDTO userDTO){
        UserDTO user = userService.logIn(userDTO.getUsername(),userDTO.getPassword());
        System.out.println(user);
        if(userDTO!=null){
            return ResponseEntity.ok(user);
        }

        else return null;
    }

    @GetMapping("/findU")
    public ResponseEntity<Object> findByUsername(@RequestParam String username){
        UserDTO user = userService.findByUsername(username);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        else return null;
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findById(@RequestParam Long id){
        UserDTO user = userService.findById(id);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        else return null;
    }


}
