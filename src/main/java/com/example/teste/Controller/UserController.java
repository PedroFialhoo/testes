package com.example.teste.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teste.Dto.UserDto;
import com.example.teste.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<?> cadastrarUser(@Valid @RequestBody UserDto userDto){
        userService.cadastrarUser(userDto);

        return ResponseEntity.ok("Usuário cadastrado");
    }
}
