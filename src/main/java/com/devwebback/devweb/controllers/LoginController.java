package com.devwebback.devweb.controllers;

import com.devwebback.devweb.dto.LoginDTO;
import com.devwebback.devweb.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/login")           
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try {
            Object user = loginService.login(loginDTO.getEmail(), loginDTO.getSenha());
            return ResponseEntity.ok(user);
        }catch (RuntimeException e){
            return  ResponseEntity.status(401).body(e.getMessage());
        }
    }

}
