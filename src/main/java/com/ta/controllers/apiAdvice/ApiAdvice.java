package com.ta.controllers.apiAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ta.dtos.Response;
import com.ta.exceptions.BadRequestException;
import com.ta.exceptions.DuplicateException;
import com.ta.exceptions.NotFoundException;

@ControllerAdvice
public class ApiAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Response exceptionHandler(BadRequestException e) {
        return new Response(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public Response exceptionHandler(NotFoundException e) {
        return new Response(String.valueOf(HttpStatus.NOT_FOUND.value()), e.getMessage());
    }
    
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public Response exceptionHandler(DuplicateException e) {
        return new Response(String.valueOf(HttpStatus.CONFLICT.value()), e.getMessage());
    }
}
