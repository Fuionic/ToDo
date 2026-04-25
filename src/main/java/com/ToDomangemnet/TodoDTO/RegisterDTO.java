package com.ToDomangemnet.TodoDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class RegisterDTO {
    private String username;
    private String name;
    private String password;
    private String email;

}
