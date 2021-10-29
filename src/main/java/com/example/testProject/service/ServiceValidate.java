package com.example.testProject.service;

import com.example.testProject.entity.DtoUser;
import com.example.testProject.entity.ResultsRequests;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pavel
 * Validate dto
 */
@Component
public class ServiceValidate {

    private static final Pattern EMAIL_PATTERN =Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public LinkedList<ResultsRequests> validateDtoObject(DtoUser checkObject){

        LinkedList<ResultsRequests> resultsRequests=new LinkedList<>();

        //check passwords
        if(!checkObject.getPassword().equals(checkObject.getCheckPassword()))
        {
            resultsRequests.add(new ResultsRequests(checkObject.toString(),"Пароли не совпадают."));

        }

        //check email
        Matcher matcher=EMAIL_PATTERN.matcher(checkObject.getEmail());
       if(!matcher.matches()){resultsRequests.add(new ResultsRequests(checkObject.toString(),"Неверный формат почты."));}


        return resultsRequests;
        }
}
