package uz.pdp.olx.exception.hendler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.olx.exception.ProductNotFoundException;

@RestControllerAdvice
public class ProductNotFoundExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(e.getMessage());
    }
}
