package com.microservicio.cliente.persona.cliente_persona.exceptions;

import com.microservicio.cliente.persona.cliente_persona.models.ResponseVo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de errores de validación
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        // Recorrer las violaciones de restricción y agregar los mensajes de error
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String simpleFieldName = fieldName.substring(fieldName.lastIndexOf('.') + 1);
            String errorMessage = violation.getMessage();
            errors.put(simpleFieldName, errorMessage);
        }

        if (!errors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            errors.forEach((field, message) -> errorMessage.append(field).append(": ").append(message).append("; "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false, errorMessage.toString(), HttpStatus.NOT_FOUND.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false, "Errores de validación no especificados", 400));
    }

    //if (!errors.isEmpty()) {
    //            StringBuilder errorMessage = new StringBuilder();
    //            errors.forEach((field, message) -> errorMessage.append(field).append(": ").append(message).append("; "));
    //            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo(false, errorMessage.toString(), 400));
    //        }
    //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo(false, "Errores de validación no especificados", 400));

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFoundException(ClientNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoClientsFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoClientsFoundException(NoClientsFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
