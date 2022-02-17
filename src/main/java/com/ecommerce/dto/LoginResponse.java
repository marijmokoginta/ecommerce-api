package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String access_token;
    private Long id;
    private String username;
    private List<String> roles;
}
