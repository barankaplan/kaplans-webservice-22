package com.spring.project01.kaplanjpahibernate.dto;


import lombok.Data;
//step 4
@Data
public class LoginDTO {
    private String userNameOrEMail;
    private String password;
}
