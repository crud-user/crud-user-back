package com.example.cruduser.DTOs;

import com.example.cruduser.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private Date dateNaissance;
    private String role;



    public static User mapDtoToUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .dateNaissance(userDto.getDateNaissance())
                .role((userDto.getRole().equals("ADMIN"))? User.UserRole.ADMIN: User.UserRole.USER)
                .build();
    }
    public static UserDto mapUserToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .userName(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .dateNaissance(user.getDateNaissance())
                .role(user.getRole().toString())
                .build();
    }
}
