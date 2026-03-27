package com.example.teste.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teste.Dto.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class UserController {

    @PostMapping
    public ResponseEntity cadastrarUser(@Valid @RequestBody UserDto userDto){
        return null;
    }
}
