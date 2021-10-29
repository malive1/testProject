package com.example.testProject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author pavel
 * Return list this info requests and results.
 */

@Data
@NoArgsConstructor
public class DtoInfoList {
    List<String> listInfo;
}
