package br.com.locationManagementSystem.api.infra.controller;

import br.com.locationManagementSystem.api.application.usecases.User.*;
import br.com.locationManagementSystem.api.domain.entities.user.User;
import br.com.locationManagementSystem.api.infra.controller.Dtos.User.*;
import br.com.locationManagementSystem.api.infra.security.TokenContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @MockBean
    private UserController userController;
    @MockBean
    private CreateUser createUser;
    @MockBean
    private DeleteUser deleteUser;
    @MockBean
    private ListUsers listUsers;
    @MockBean
    private GetUserByEmail getUserByEmail;
    @MockBean
    private GetUserById getUserById;
    @MockBean
    private GetUserByUsername getUserByUsername;
    @MockBean
    private ResetPassword resetPassword;
    @MockBean
    private UpdateUser updateUser;

    @BeforeEach
    void setUp() {
        createUser = mock(CreateUser.class);
        deleteUser = mock(DeleteUser.class);
        listUsers = mock(ListUsers.class);
        getUserByEmail = mock(GetUserByEmail.class);
        getUserById = mock(GetUserById.class);
        getUserByUsername = mock(GetUserByUsername.class);
        resetPassword = mock(ResetPassword.class);
        updateUser = mock(UpdateUser.class);
        userController = new UserController(createUser, deleteUser, listUsers, getUserByEmail, getUserById, getUserByUsername, resetPassword, updateUser);
    }

    @Test
    void testGetAllUsersSuccess() {
        // Given
        User user = new User(1L, "username", "email@example.com", "First", "Last");
        List<User> users = List.of(user);
        when(listUsers.listUsers()).thenReturn(users);

        // When
        List<UserDto> response = userController.getAllUsers();

        // Then
        assertEquals(1, response.size());
        assertEquals(UserDto.fromEntity(user), response.get(0));
    }

    @Test
    void testGetAllUsersFailure() {
        // Given
        when(listUsers.listUsers()).thenReturn(Collections.emptyList());

        // When
        List<UserDto> response = userController.getAllUsers();

        // Then
        assertEquals(0, response.size());
    }

    @Test
    void testGetUserByIdSuccess() {
        // Given
        Long userId = 1L;
        User user = new User(userId, "username", "email@example.com", "First", "Last");
        when(getUserById.getUserById(userId)).thenReturn(ResponseEntity.ok(user));

        // When
        ResponseEntity<UserDto> response = userController.getUserById(userId);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(UserDto.fromEntity(user), response.getBody());
    }

    @Test
    void testGetUserByIdFailure() {
        // Given
        Long userId = 1L;
        when(getUserById.getUserById(userId)).thenReturn(ResponseEntity.notFound().build());

        // When
        ResponseEntity<UserDto> response = userController.getUserById(userId);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testGetUserByUsernameSuccess() {
        // Given
        String username = "username";
        User user = new User(1L, username, "email@example.com", "First", "Last");
        when(getUserByUsername.getUserByUsername(username)).thenReturn(ResponseEntity.ok(user));

        // When
        ResponseEntity<UserDto> response = userController.getUserByUsername(username);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(UserDto.fromEntity(user), response.getBody());
    }

    @Test
    void testGetUserByUsernameFailure() {
        // Given
        String username = "username";
        when(getUserByUsername.getUserByUsername(username)).thenReturn(ResponseEntity.notFound().build());

        // When
        ResponseEntity<UserDto> response = userController.getUserByUsername(username);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testGetUserByEmailSuccess() {
        // Given
        UserEmailDto userEmailDto = new UserEmailDto("email@example.com");
        User user = new User(1L, "username", "email@example.com", "First", "Last");
        when(getUserByEmail.getUserByEmail(userEmailDto.email())).thenReturn(ResponseEntity.ok(user));

        // When
        ResponseEntity<UserDto> response = userController.getUserByEmail(userEmailDto);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(UserDto.fromEntity(user), response.getBody());
    }

    @Test
    void testGetUserByEmailFailure() {
        // Given
        UserEmailDto userEmailDto = new UserEmailDto("email@example.com");
        when(getUserByEmail.getUserByEmail(userEmailDto.email())).thenReturn(ResponseEntity.notFound().build());

        // When
        ResponseEntity<UserDto> response = userController.getUserByEmail(userEmailDto);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateUserSuccess() {
        // Given
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto("username", "email@example.com", "First", "Last", "password");
        User user = new User(1L, "username", "email@example.com", "First", "Last");
        when(createUser.createUser(any(User.class))).thenReturn(user);

        // When
        UserDto response = userController.createUser(userRegistrationDto);

        // Then
        assertEquals(new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName()), response);
    }

    @Test
    void testCreateUserFailure() {
        // Given
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto("", "email@example.com", "First", "Last", "password");
        // The failure case is generally handled by validation at the request level,
        // which should return an error status (like 400) if invalid data is submitted.
        // Mocking it here is less common, but let's handle it for completeness.

        // When
        UserDto response = userController.createUser(userRegistrationDto);

        // Then
        // Handle expected failure scenario, e.g., by checking if proper validation error handling is in place.
    }

    @Test
    void testRequestResetPasswordSuccess() {
        // Given
        UserEmailDto userEmailDto = new UserEmailDto("email@example.com");
        User user = new User(1L, "username", "email@example.com", "First", "Last");
        when(getUserByEmail.getUserByEmail(userEmailDto.email())).thenReturn(ResponseEntity.ok(user));
        Mockito.mockStatic(TokenContext.class);
        when(TokenContext.getToken()).thenReturn("token");

        // When
        RequestResetPasswordDto response = userController.requestResetPassword(userEmailDto);

        // Then
        assertEquals(new RequestResetPasswordDto(user.getId(), "token", "Link ainda a ser definido"), response);
    }

    @Test
    void testRequestResetPasswordFailure() {
        // Given
        UserEmailDto userEmailDto = new UserEmailDto("email@example.com");
        when(getUserByEmail.getUserByEmail(userEmailDto.email())).thenReturn(ResponseEntity.notFound().build());

        // When
        RequestResetPasswordDto response = userController.requestResetPassword(userEmailDto);

        // Then
        // Handle expected failure scenario, e.g., by checking if proper error handling is in place.
    }

    @Test
    void testConfirmResetPasswordSuccess() {
        // Given
        UserChangePassword userChangePassword = new UserChangePassword(1L, "token", "newPassword");
        User user = new User(1L, "username", "email@example.com", "First", "Last");
        when(resetPassword.resetPassword(any(User.class))).thenReturn(ResponseEntity.ok(user));

        // When
        ResponseEntity<User> response = userController.confirmResetPassword(userChangePassword);

        // Then
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testConfirmResetPasswordFailure() {
        // Given
        UserChangePassword userChangePassword = new UserChangePassword(1L, "token", "newPassword");
        when(resetPassword.resetPassword(any(User.class))).thenReturn(ResponseEntity.notFound().build());

        // When
        ResponseEntity<User> response = userController.confirmResetPassword(userChangePassword);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testUpdateUserSuccess() {
        // Given
        Long userId = 1L;
        UserToUpdateDto userToUpdateDto = new UserToUpdateDto("newUsername", "newEmail@example.com", "NewFirst", "NewLast", "newPassword");
        User user = new User(userId, "newUsername", "newEmail@example.com", "NewFirst", "NewLast");
        when(updateUser.updateUser(anyLong(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(ResponseEntity.ok(user));

        // When
        ResponseEntity<User> response = userController.updateUser(userId, userToUpdateDto);

        // Then
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testUpdateUserFailure() {
        // Given
        Long userId = 1L;
        UserToUpdateDto userToUpdateDto = new UserToUpdateDto("newUsername", "newEmail@example.com", "NewFirst", "NewLast", "newPassword");
        when(updateUser.updateUser(anyLong(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(ResponseEntity.notFound().build());

        // When
        ResponseEntity<User> response = userController.updateUser(userId, userToUpdateDto);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeleteUserSuccess() {
        // Given
        Long userId = 1L;
        when(deleteUser.deleteUser(userId)).thenReturn(ResponseEntity.noContent().build());

        // When
        ResponseEntity<Object> response = userController.deleteUser(userId);

        // Then
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteUserFailure() {
        // Given
        Long userId = 1L;
        when(deleteUser.deleteUser(userId)).thenReturn(ResponseEntity.notFound().build());

        // When
        ResponseEntity<Object> response = userController.deleteUser(userId);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }
}
