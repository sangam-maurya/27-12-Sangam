package com.main.excpection;

import com.main.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalHandel {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDto> handelException(ResourceNotFound resourceNotFound , WebRequest web){
        ErrorDto errorDto = new ErrorDto(resourceNotFound.getMessage(), new Date(), web.getDescription(true));
        return new ResponseEntity<>(errorDto , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>globalHandelException(Exception e){
        return ResponseEntity.ok(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
