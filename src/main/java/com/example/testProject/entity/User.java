package com.example.testProject.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * @author pavel
 * Entetu class - User
 */
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    //    str.matches("[\\w-']+");
    @NonNull
    @Pattern(regexp = "\\p{L}+", message = "Только буквы.")
     String surname;

    @NonNull
    @Pattern(regexp = "\\p{L}+", message = "Только буквы.")
     String name;

    @NonNull
    @Pattern(regexp = "\\p{L}+", message = "Только буквы.")
     String middleName;

    @NonNull
    @Pattern(regexp = "\\+7[0-9]{10}", message = "Пример номера: +7(XXX)NNNNNNN. XXX - код оператора, NNNNNNN - номер телефона.")
     String phone;

    @NonNull
    @Email(message = "Поле должно содержать @ .")
     String email;

    @NonNull
     char[] password;

    @NonNull
    char[] checkPassword;


}
