package com.example.testProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pavel
 * Row info for list requests rezults.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowInfo {
    int id;
    String value;
}
