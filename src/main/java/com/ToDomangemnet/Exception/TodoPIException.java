package com.ToDomangemnet.Exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class TodoPIException extends RuntimeException{

    private HttpStatus status;
    private String message;


}
