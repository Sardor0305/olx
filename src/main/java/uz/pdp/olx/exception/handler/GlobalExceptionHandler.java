package uz.pdp.olx.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.olx.exception.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<?> handleTokenNotFoundException(TokenNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exception.getMessage());
    }
    @ExceptionHandler(TokenIsExpiredException.class)
    public ResponseEntity<?> handleTokenIsExpiredException(TokenIsExpiredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(exception.getMessage());
    }
    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistException(AlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(e.getMessage());
    }
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(e.getMessage());
    }
}
