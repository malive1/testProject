package com.example.testProject.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author pavel
 * Work requests exeptions
 */
@RestControllerAdvice
public class ErrorService {
    private final WorkService workService;

    public ErrorService(WorkService workService) {
        this.workService = workService;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> validationBodyException(MethodArgumentNotValidException exception){
        AtomicReference<String> errMessage = null;
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();

            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                errMessage.set(fieldError.getDefaultMessage());
               });
            workService.addValidInfo("test");
        }
        return new ResponseEntity<>(errMessage.toString(), HttpStatus.BAD_REQUEST);
    }
}
