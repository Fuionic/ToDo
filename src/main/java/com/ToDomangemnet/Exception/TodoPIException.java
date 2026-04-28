package com.ToDomangemnet.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TodoPIException extends RuntimeException{

    private HttpStatus status;
    private String message;


}
