package com.security.demo.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotExistException extends RuntimeException{

    private String id;

    public UserNotExistException(String id) {
        super("User not exist Exception");
        this.id=id;
    }
}
