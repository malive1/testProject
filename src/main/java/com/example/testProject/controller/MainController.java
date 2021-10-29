package com.example.testProject.controller;

import com.example.testProject.entity.DtoInfoList;
import com.example.testProject.entity.DtoListValidate;
import com.example.testProject.entity.DtoUser;
import com.example.testProject.entity.ResultsRequests;
import com.example.testProject.service.ServiceValidate;
import com.example.testProject.service.WorkService;
import com.example.testProject.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;


/**
 * @author pavel 29.10.2021
 * REST Controller class
 */

@RestController
@Validated
@RequestMapping("/test/service")
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
     * @param dtoUser - json data for dto object
     * @return - state
     */
    @PostMapping(value = "/addUser", consumes = "application/json",produces = "application/json")
    @SneakyThrows({org.springframework.http.converter.HttpMessageNotReadableException.class})
    public ResponseEntity<DtoListValidate> addUser(@Validated @RequestBody  DtoUser dtoUser){
        DtoListValidate infoValidate = new DtoListValidate();
        LinkedList<ResultsRequests> checkInfo = serviceValidate.validateDtoObject(dtoUser);
        infoValidate.setInfo(serviceValidate.validateDtoObject(dtoUser));


        if(checkInfo.size()>0){

            workService.addValidInfo(dtoUser,utils.listAggInfo((LinkedList<ResultsRequests>) infoValidate.getInfo()));

            return new ResponseEntity<DtoListValidate>(infoValidate, HttpStatus.BAD_REQUEST);
        }else{

        workService.addNewUser(dtoUser);}

return new ResponseEntity<DtoListValidate>(infoValidate, HttpStatus.OK);
    }

    /**
     * Get list this informations
     * @return - JSON list<String>
     */
    @GetMapping(value = "/getInfo", produces = "application/json")
    public ResponseEntity<DtoInfoList> getInfo(){
        DtoInfoList info = new DtoInfoList();
        info.setListInfo(workService.getInfo());
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
