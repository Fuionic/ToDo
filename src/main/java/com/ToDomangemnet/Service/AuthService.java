package com.ToDomangemnet.Service;

import com.ToDomangemnet.TodoDTO.LoginDTO;
import com.ToDomangemnet.TodoDTO.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);

    String login(LoginDTO loginDTO);
}
