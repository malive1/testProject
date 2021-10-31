package com.example.testProject.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * @author pavel
 * Entetu class - User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    int id;

    String surname;

    String name;

    String middleName;

    String phone;

    String email;

    char[] password;

    char[] checkPassword;


}
