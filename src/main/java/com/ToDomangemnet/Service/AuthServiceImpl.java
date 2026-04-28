package com.ToDomangemnet.Service;

import com.ToDomangemnet.Entity.Role;
import com.ToDomangemnet.Entity.USer;
import com.ToDomangemnet.Exception.TodoPIException;
import com.ToDomangemnet.Repository.RoleRepository;
import com.ToDomangemnet.Repository.UserRepository;
import com.ToDomangemnet.TodoDTO.LoginDTO;
import com.ToDomangemnet.TodoDTO.RegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;


    @Override
    public String register(RegisterDTO registerDTO) {

        if(userRepository.existsByUsername(registerDTO.getUsername())){
           throw new TodoPIException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }

        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new TodoPIException(HttpStatus.BAD_REQUEST, "Email is already taken");
        }

        USer user = new USer();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User logged-in successfully";
    }
}
