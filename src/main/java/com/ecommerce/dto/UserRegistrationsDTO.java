package com.ecommerce.dto;

import lombok.Data;

@Data
public class UserRegistrationsDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
