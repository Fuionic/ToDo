package com.ToDomangemnet.Security;

import com.ToDomangemnet.Entity.USer;
import com.ToDomangemnet.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class CoustomUserDetails implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException{
        USer user  = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority > authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
