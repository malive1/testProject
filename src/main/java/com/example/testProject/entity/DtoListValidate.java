package com.example.testProject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author pavel
 * DTO for list this validate info
 */

@Data
@NoArgsConstructor
public class DtoListValidate {
    List<ResultsRequests> info;
}
