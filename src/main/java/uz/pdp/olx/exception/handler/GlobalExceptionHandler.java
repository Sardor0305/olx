package uz.pdp.olx.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.olx.exception.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseBody
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<String>handPasswordNotMatch(PasswordNotMatchException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }



}
