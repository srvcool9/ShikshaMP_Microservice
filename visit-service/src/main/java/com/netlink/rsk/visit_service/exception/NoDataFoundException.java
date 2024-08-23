package com.netlink.rsk.visit_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoDataFoundException extends NoSuchElementException{

    public NoDataFoundException(String message){
        super(message);
    }
}
