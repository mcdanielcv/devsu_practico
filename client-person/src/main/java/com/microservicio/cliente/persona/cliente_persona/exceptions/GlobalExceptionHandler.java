package com.microservicio.cliente.persona.cliente_persona.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    // Manejo de errores de validación
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Error de constraint violation de datos: ", ex);
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

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateCardIdException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateCardIdException(DuplicateCardIdException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerException ex) {
        log.info("error internal server.." + ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
