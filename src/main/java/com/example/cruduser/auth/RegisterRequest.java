package com.example.cruduser.auth;


import com.example.cruduser.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String userName;
    private String email;
    private String password;
    private Date dateNaissance;
    private User.UserRole role;
}