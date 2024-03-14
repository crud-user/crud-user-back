package com.example.cruduser.services;


import com.example.cruduser.DTOs.UserDto;
import com.example.cruduser.dao.criteria.UserCriteriaParametrage;
import com.example.cruduser.dao.specification.UserSpecificationParametrage;
import com.example.cruduser.models.User;
import com.example.cruduser.models.UserSpecifications;
import com.example.cruduser.repositorises.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> UserDto.mapUserToDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserDto.mapUserToDto(userRepository.save(UserDto.mapDtoToUser(user)));
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userdto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("this user do not exist"));
        user.setUserName(userdto.getUserName());
        user.setName(userdto.getName());
        user.setDateNaissance(userdto.getDateNaissance());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        return UserDto.mapUserToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user1 = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("this user do not exist"));
         userRepository.delete(user1);
    }

    @Override
    public List<User> likeName(String name) {
        return userRepository.findAll(UserSpecifications.likeName(name));
    }

    public List<UserDto> findUsersByCriteria(UserCriteriaParametrage userCriteriaParametrage) throws Exception {

        Specification<User> specification = new UserSpecificationParametrage(userCriteriaParametrage);
            return userRepository.findAll(specification).stream()
                    .map(UserDto::mapUserToDto).collect(Collectors.toList());
    }
}
