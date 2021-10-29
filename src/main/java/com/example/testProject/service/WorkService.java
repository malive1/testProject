package com.example.testProject.service;

import com.example.testProject.entity.DtoUser;
import com.example.testProject.entity.ResultsRequests;
import com.example.testProject.entity.User;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author pavel
 * Main work servise
 */

@Service
public class WorkService {
    private LinkedList<ResultsRequests> resultsRequests;
    private LinkedList<User> listUser;

    /**
     * Default constructor.
     * Init results list.
     */
    public WorkService(){
        resultsRequests = new LinkedList<>();
        listUser = new LinkedList<>();
        resultsRequests.add(new ResultsRequests("EMPTY","Ни один пользователь не зарегистрирован."));
    }

    /**
     * Return list this results for requests.
     * @return - list this info
     */
    public List<String> getInfo(){

        Iterable<String> listRez = Iterables.transform(resultsRequests,
                new Function<ResultsRequests, String>() {
                    public String apply(ResultsRequests from) {
                        return from.getResultInformation();
                    }
                });

        List<String> actualList = Lists.newArrayList(listRez);
        return actualList;
    }

    /**
     * Add new user.
     * @param inUser - DtoUser object
     */
    public void addNewUser(DtoUser inUser){
System.out.println("-----");
        //clear start info in result requests
         if((resultsRequests.size()>=1) && (resultsRequests.getFirst().getInputData().equals("EMPTY")))
         {
             resultsRequests.removeFirst();
         }

         //check passwords
        if(!inUser.getPassword().equals(inUser.getCheckPassword()))
        {
            resultsRequests.add(new ResultsRequests(inUser.toString(),"Пароли не совпадают."));
            return;
        }

        User usrTmp = new User();
        usrTmp.setEmail(inUser.getEmail());
        usrTmp.setName(inUser.getName());
        usrTmp.setSurname(inUser.getSurname());
        usrTmp.setMiddleName(inUser.getMiddleName());
        usrTmp.setPhone(inUser.getPhone());
        usrTmp.setPassword(inUser.getPassword().toCharArray());
        usrTmp.setCheckPassword(inUser.getCheckPassword().toCharArray());

        listUser.add(usrTmp);
        resultsRequests.add(new ResultsRequests(inUser.toString(),"Регистрация успешна."));
    }

public void addValidInfo(String inInfo){
    resultsRequests.add(new ResultsRequests("ERROR",inInfo));
}

}
