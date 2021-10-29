package com.example.testProject.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * @author pavel 29.10.2021
 * DTO for User class
 */

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class DtoUser {
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


    @Email(message = "Поле должно содержать @ .")
    String email;

    @NonNull
    String password;

    @NonNull
    String checkPassword;
}
