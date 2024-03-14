package com.example.cruduser.dao.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
public class UserCriteriaParametrage {

    private String userName;
    private String email;
    private Date dateNaissance;
}
