package com.example.testProject.service;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author pavel
 * Work requests exeptions
 */
@ControllerAdvice
public class ErrorService {
    private final WorkService workService;

    public ErrorService(WorkService workService) {
        this.workService = workService;
    }

    /*@ExceptionHandler({org.springframework.http.converter.HttpMessageNotReadableException.class})
    public ResponseEntity<String> validationBodyException(Exception exception){
        System.out.println("test ->>> "+exception.getClass());
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
        return new ResponseEntity<>("errMessage.toString()", HttpStatus.BAD_REQUEST);
    }*/

    /**
     *Work exeption from REST
     * @param exception - Exception
     */
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> processException(org.springframework.http.converter.HttpMessageNotReadableException exception)  {


        System.out.println("test ->>> "+exception.getClass());

        workService.addErrValidInfo(exception.getLocalizedMessage());
        return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }


}
