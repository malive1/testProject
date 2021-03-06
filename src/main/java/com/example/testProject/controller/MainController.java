package com.example.testProject.controller;

import com.example.testProject.entity.*;
import com.example.testProject.service.ServiceValidate;
import com.example.testProject.service.WorkService;
import com.example.testProject.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


/**
 * @author pavel 29.10.2021
 * REST Controller class
 */

@RestController
@Validated
@RequestMapping("/test/service")
@CrossOrigin
public class MainController {
    private final WorkService workService;

    private final ServiceValidate serviceValidate;
    private final Utils utils;

    public MainController(WorkService workService, ServiceValidate serviceValidate, Utils utils) {
        this.workService = workService;
        this.serviceValidate = serviceValidate;
        this.utils = utils;
    }

    /**
     * Add new user
     *
     * @param dtoUser - json data for dto object
     * @return - state
     */
    @PostMapping(value = "/addUser", consumes = "application/json", produces = "application/json")
    @SneakyThrows({org.springframework.http.converter.HttpMessageNotReadableException.class})
    public ResponseEntity<DtoListValidate> addUser(@Validated @RequestBody DtoUser dtoUser) {
        DtoListValidate infoValidate = new DtoListValidate();
        LinkedList<ResultsRequests> checkInfo = serviceValidate.validateDtoObject(dtoUser);
        infoValidate.setInfo(serviceValidate.validateDtoObject(dtoUser));

        workService.refreshHistoryEventIfo();

        if (checkInfo.size() > 0) {

            workService.addValidInfo(dtoUser, utils.listAggInfo((LinkedList<ResultsRequests>) infoValidate.getInfo()));

            return new ResponseEntity<DtoListValidate>(infoValidate, HttpStatus.BAD_REQUEST);
        } else {

            workService.addNewUser(dtoUser);
        }

        return new ResponseEntity<DtoListValidate>(infoValidate, HttpStatus.OK);
    }

    /**
     * Get list this informations
     *
     * @return - JSON list<String>
     */
    @GetMapping(value = "/getInfo", produces = "application/json")
    public List<RowInfo> getInfo() {
        DtoInfoList info = new DtoInfoList();
        info.setListInfo(workService.getInfo());
        return info.getListInfo();
    }

    /**
     * Return UserList data for front.
     * @return - List<DtoUser>
     */
    @GetMapping(value = "/getInfoUsersAll", produces = "application/json")
    public List<DtoUser> getInfoUsers() {

        return workService.getViewList();
    }

    /**
     * Return User  for front.
     * @return - DtoUser
     */
    @GetMapping(value = "/getInfoUser/{id}", produces = "application/json")
    public DtoUser getInfoUser(@PathVariable int id) {

        return workService.getUser(id);
    }
}
