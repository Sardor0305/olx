package uz.pdp.olx.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.olx.exception.ProductNotFoundException;
import uz.pdp.olx.exception.TokenIsExpiredException;
import uz.pdp.olx.exception.TokenNotFoundException;
import uz.pdp.olx.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFondException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exception.getMessage());
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<?> handleTokenNotFoundException(TokenNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exception.getMessage());
    }

    @ExceptionHandler(TokenIsExpiredException.class)
    public ResponseEntity<?> handleTokenIsExpiredException(TokenIsExpiredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(exception.getMessage());
    }
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(e.getMessage());
    }



}
