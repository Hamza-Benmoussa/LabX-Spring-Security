package com.example.labxspringboot.exception.advice;

import com.example.labxspringboot.exception.exept.EmailDejaExisteException;
import com.example.labxspringboot.exception.exept.UtilisateurFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String ,String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String , String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());

        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UtilisateurFoundException.class)
    public Map<String , String> handleBusinessException(UtilisateurFoundException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmailDejaExisteException.class)
    public Map<String , String> handleEmailException(EmailDejaExisteException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }

}
