package com.example.cruduser;

import com.example.cruduser.DTOs.UserDto;
import com.example.cruduser.controller.UserController;
import com.example.cruduser.models.User;
import com.example.cruduser.repositorises.IUserRepository;
import com.example.cruduser.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetAllUsers(){
        // Création de données de test
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto(1L,"John", "john1","123","john@gmail.com",Date.valueOf("2019-10-15"),"Admin"));
        users.add(new UserDto(2L,"Alice", "alice1","123","alice@gmail.com",Date.valueOf("2019-10-15"),"Admin"));

        when(userService.getAllUsers()).thenReturn(users);

        // Appeler la méthode du contrôleur à tester
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        // Vérifier si le statut de la réponse est OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Vérifier si la liste retournée correspond à celle attendue
        assertEquals(users, response.getBody());

        // Vérifier si la méthode du service a été appelée une fois
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testAddUser() {
        // Création de données de test
        UserDto userDto = new UserDto(null,"John", "john1","123","john@gmail.com",Date.valueOf("2019-10-15"),"Admin");
        UserDto savedUserDto = new UserDto(1L,"John", "john1","123","john@gmail.com",Date.valueOf("2019-10-15"),"Admin");

        // Définir le comportement attendu du userService.addUser()
        when(userService.addUser(userDto)).thenReturn(savedUserDto);

        // Appeler la méthode du contrôleur à tester
        ResponseEntity<UserDto> response = userController.addUser(userDto);

        // Vérifier si le statut de la réponse est OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Vérifier si l'utilisateur retourné correspond à celui attendu
        assertEquals(savedUserDto, response.getBody());

    }

    @Test
    public void testUpdateUser() {
        // Données de test
        Long userId = 1L;
        UserDto userDtoToUpdate = new UserDto(userId, "UpdatedName", "updatedUsername", "updatedPassword", "updatedEmail", null, "Admin");
        when(userService.updateUserById(userId, userDtoToUpdate)).thenReturn(userDtoToUpdate);
        when(userRepository.findById(userId)).thenReturn(Optional.of(UserDto.mapDtoToUser(userDtoToUpdate)));

        // Appel de la méthode à tester
        ResponseEntity<UserDto> response = userController.updateUser(userId, userDtoToUpdate);

        // Vérifications
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Vérifie le statut de réponse

        UserDto updatedUserDto = response.getBody();
        assertEquals(userDtoToUpdate.getName(), updatedUserDto.getName()); // Vérifie si le nom a été mis à jour
        assertEquals(userDtoToUpdate.getUserName(), updatedUserDto.getUserName()); // Vérifie si le nom d'utilisateur a été mis à jour
        assertEquals(userDtoToUpdate.getEmail(), updatedUserDto.getEmail()); // Vérifie si l'email a été mis à jour

        // Vérifie si la méthode du service a été appelée avec les bons arguments
        verify(userService, times(1)).updateUserById(userId, userDtoToUpdate);
    }

    @Test
    public void testDeleteUser() {
        // Données de test
        Long userId = 1L;
        User userDtoToDelete= new User(userId, "UpdatedName", "updatedUsername", "updatedPassword", "updatedEmail", null, null);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userDtoToDelete));
        assertAll(()-> userController.deleteUser(userId));
    }

}
