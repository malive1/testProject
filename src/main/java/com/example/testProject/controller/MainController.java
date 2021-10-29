package com.example.testProject.controller;

import com.example.testProject.entity.DtoInfoList;
import com.example.testProject.entity.DtoUser;
import com.example.testProject.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author pavel 29.10.2021
 * REST Controller class
 */

@RestController
@Validated
@RequestMapping("/test/service")
public class MainController {
private final WorkService workService;

    public MainController(WorkService workService) {
        this.workService = workService;
    }

    /**
     * Add new user
     * @param dtoUser - json data for dto object
     * @return - state
     */
    @PostMapping(value = "/addUser", consumes = "application/json"/*,produces = "application/json"*/)
    public ResponseEntity<String> addUser(@RequestBody @Valid DtoUser dtoUser){
        workService.addNewUser(dtoUser);
return new ResponseEntity<>("OK", HttpStatus.OK);
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
