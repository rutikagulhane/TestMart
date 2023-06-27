package com.example.TestMart.advice;

import com.example.TestMart.exceptions.CartNotFoundException;
import com.example.TestMart.exceptions.ProductNotAvailableException;
import com.example.TestMart.exceptions.UserNotFoundException;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @SneakyThrows
    @ExceptionHandler(ProductNotAvailableException.class)
    public final ResponseEntity<?> handleProductNotAvailableExcpetion(ProductNotAvailableException p )  {
        JSONObject response=new JSONObject();
        response.put("message",p.getMessage());
        return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);

    }

    @SneakyThrows
    @ExceptionHandler(CartNotFoundException.class)
    public final ResponseEntity<?> handleCartNotFoundException(CartNotFoundException c)
    {
        JSONObject response=new JSONObject();
        response.put("message",c.getMessage());
        return new ResponseEntity<>(response.toString(),HttpStatus.NOT_FOUND);


    }

    @SneakyThrows
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<?> handleUserNotFoundException(UserNotFoundException u)
    {
        JSONObject response=new JSONObject();
        response.put("message",u.getMessage());
        return new ResponseEntity<>(response.toString(),HttpStatus.NOT_FOUND);
    }
}
