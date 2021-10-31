package com.example.testProject.service;

import com.example.testProject.entity.*;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    public WorkService() {
        resultsRequests = new LinkedList<>();
        listUser = new LinkedList<>();
        resultsRequests.add(new ResultsRequests("EMPTY", "Ни один пользователь не зарегистрирован."));
    }

    /**
     * Return list this results for requests.
     *
     * @return - list this info
     */
    public List<RowInfo> getInfo() {


        List<RowInfo> actualList = new LinkedList<>();
        int indexRow = 0;

        for (ResultsRequests value : resultsRequests) {

            actualList.add(new RowInfo(indexRow, value.getResultInformation()));
            indexRow++;
        }

        return actualList;
    }


    /**
     * Add new user.
     *
     * @param inUser - DtoUser object
     */
    public void addNewUser(DtoUser inUser) {

        int idNum = listUser.size();


        User usrTmp = new User();
        usrTmp.setId(idNum);
        usrTmp.setEmail(inUser.getEmail());
        usrTmp.setName(inUser.getName());
        usrTmp.setSurname(inUser.getSurname());
        usrTmp.setMiddleName(inUser.getMiddleName());
        usrTmp.setPhone(inUser.getPhone());
        usrTmp.setPassword(inUser.getPassword().toCharArray());
        usrTmp.setCheckPassword(inUser.getCheckPassword().toCharArray());

        listUser.add(usrTmp);
        resultsRequests.add(new ResultsRequests(inUser.toString(), "Регистрация успешна."));
    }

    /**
     * Add info catched exeptions from request validate
     *
     * @param inInfo - error informations
     */
    public void addErrValidInfo(String inInfo) {
        resultsRequests.add(new ResultsRequests("ERROR", inInfo));
    }

    /**
     * Add validate info. Check input dao object fields.
     *
     * @param inDao  - input dao
     * @param inInfo - store validation  info
     */
    public void addValidInfo(DtoUser inDao, String inInfo) {
        resultsRequests.add(new ResultsRequests(inDao.toString(), inInfo));
    }

    /**
     * Sinchronize event info log.
     */
    public void refreshHistoryEventIfo() {

        //clear start info in result requests
        if ((resultsRequests.size() >= 1) && (resultsRequests.getFirst().getValueKeyData().equals("EMPTY"))) {
            resultsRequests.removeFirst();
        }

        //clear old records. Store last 3 events
        if (resultsRequests.size() > 2) {
            resultsRequests.removeFirst();

        }


    }

    /**
     * For front. Return Dto list users - set password to ******.
     * @return - List<DtoUser> this ****** password and checkpassword.
     */
    public List<DtoUser> getViewList() {
        List<DtoUser> viewList = new LinkedList<>();

        int num = 0;
        while (listUser.size() > num) {
            DtoUser tmpUser = new DtoUser(listUser.get(num).getId(),listUser.get(num).getSurname(),
                    listUser.get(num).getName(), listUser.get(num).getMiddleName(), listUser.get(num).getPhone(), listUser.get(num).getEmail(),
                    "******", "******");

            viewList.add(tmpUser);
            num++;
        }



        return viewList;
    }

    /**
     *Get user by id.
     */
    public DtoUser getUser(int id){

        User usrTmp = listUser.get(id);
        DtoUser dUser=null;
        if(usrTmp !=null)
        {
         dUser =  new DtoUser(usrTmp.getId(),usrTmp.getSurname(),
                usrTmp.getName(), usrTmp.getMiddleName(), usrTmp.getPhone(), usrTmp.getEmail(),
                "******", "******");}
        return dUser;
    }


}
