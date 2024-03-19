package com.example.cruduser.services;

import com.example.cruduser.DTOs.UserDto;
import com.example.cruduser.dao.criteria.UserCriteriaParametrage;
import com.example.cruduser.models.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();

    UserDto addUser(UserDto user);

    UserDto updateUserById(Long id, UserDto userdto);

    void deleteUser(Long id);

    List<User> likeName(String name);
    public List<UserDto> findUsersByCriteria(UserCriteriaParametrage userCriteriaParametrage) throws Exception;

    Page<UserDto> getUsersByCriteriaAndPagination(UserCriteriaParametrage categorieParametrageCriteria);
}
