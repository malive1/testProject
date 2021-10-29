package com.example.testProject.service;

import com.example.testProject.entity.DtoUser;
import com.example.testProject.entity.ResultsRequests;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pavel
 * Validate dto
 */
@Service
public class ServiceValidate {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    private static final Pattern FIO_PATTERN = Pattern.compile("[a-zA-Z]+");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+[\\(\\-]?(\\d[\\(\\)\\-]?){10}\\d$");

    /**
     * Validate fields input DtoUser object
     * @param checkObject - check DtoUser object
     * @return - list validate error or empty list
     */
    public LinkedList<ResultsRequests> validateDtoObject(DtoUser checkObject){

        LinkedList<ResultsRequests> resultsRequests=new LinkedList<>();

        //check passwords
        if(!checkObject.getPassword().equals(checkObject.getCheckPassword()))
        {
            resultsRequests.add(new ResultsRequests(checkObject.toString(),"Пароли не совпадают."));

        }

        //check email
        Matcher matcher=EMAIL_PATTERN.matcher(checkObject.getEmail());
       if(!matcher.matches()){resultsRequests.add(new ResultsRequests("MAIL","Неверный формат почты."));}

       //Check Name
        Matcher matcherName = FIO_PATTERN.matcher(checkObject.getName());
        if(!matcherName.matches()){resultsRequests.add(new ResultsRequests("NAME","Имя не соответсвует парметрам."));}

        //Check Surname
        Matcher matcherSurname = FIO_PATTERN.matcher(checkObject.getSurname());
        if(!matcherSurname.matches()){resultsRequests.add(new ResultsRequests("SURNAME","Фамилия не соответсвует парметрам."));}

        //Check MiddleName
        Matcher matcherMiddleName = FIO_PATTERN.matcher(checkObject.getMiddleName());
        if(!matcherMiddleName.matches()){resultsRequests.add(new ResultsRequests("MIDDLENAME","Отчество не соответсвует парметрам."));}

        //check phone
        Matcher matcherPhone = PHONE_PATTERN.matcher(checkObject.getPhone());
        if(!matcherPhone.matches()){resultsRequests.add(new ResultsRequests("PHONE","Неверный номер телефона."));}

        //check password
        if(!checkObject.getPassword().equals(checkObject.getCheckPassword())){
            resultsRequests.add(new ResultsRequests("PASSWORD","Пароли не совпадают."));
        }

        return resultsRequests;
        }


}
