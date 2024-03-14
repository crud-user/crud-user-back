package com.example.cruduser.controller;


import com.example.cruduser.DTOs.UserDto;
import com.example.cruduser.dao.criteria.UserCriteriaParametrage;
import com.example.cruduser.models.User;
import com.example.cruduser.services.IUserService;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add-user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userdto){
        return ResponseEntity.ok(userService.addUser(userdto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDto userdto){
        return ResponseEntity.ok(userService.updateUserById(id,userdto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/specification")
    public List<User> usersByName(@RequestParam String name){
        return userService.likeName(name);
    }

    @PostMapping("/list-by-criteria")
    public ResponseEntity<List<UserDto>> getUsersByCriteria(@RequestBody UserCriteriaParametrage categorieParametrageCriteria) throws Exception {

        List<UserDto> list = userService.findUsersByCriteria(categorieParametrageCriteria);
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);

    }

}
