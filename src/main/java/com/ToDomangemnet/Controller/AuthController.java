package com.ToDomangemnet.Controller;

import com.ToDomangemnet.Service.AuthService;
import com.ToDomangemnet.TodoDTO.LoginDTO;
import com.ToDomangemnet.TodoDTO.RegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auht")
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginDTO loginDTO){
        String response = authService.login(loginDTO);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
}
