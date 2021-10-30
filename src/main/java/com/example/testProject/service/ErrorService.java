package com.example.testProject.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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
     * Work exeption from REST
     *
     * @param exception - Exception
     */
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> processException(org.springframework.http.converter.HttpMessageNotReadableException exception) {


        workService.addErrValidInfo("Не инициализированная переменная (отсутствует поле): " + exception.getLocalizedMessage());

        return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }


}
