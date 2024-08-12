package br.com.locationManagementSystem.api.infra.controller;

import br.com.locationManagementSystem.api.application.usecases.User.*;
import br.com.locationManagementSystem.api.domain.entities.user.User;
import br.com.locationManagementSystem.api.infra.controller.Dtos.User.*;
import br.com.locationManagementSystem.api.infra.security.TokenContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")

public class UserController {
    private final CreateUser createUser;
    private final DeleteUser deleteUser;
    private final ListUsers listUsers;

    private final GetUserByEmail getUserByEmail;

    private final GetUserById getUserById;

    private final GetUserByUsername getUserByUsername;

    private final ResetPassword resetPassword;
    private final UpdateUser updateUser;

    public UserController(CreateUser createUser, DeleteUser deleteUser, ListUsers listUsers, GetUserByEmail getUserByEmail, GetUserById getUserById, GetUserByUsername getUserByUsername, ResetPassword resetPassword, UpdateUser updateUser) {
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.listUsers = listUsers;
        this.getUserByEmail = getUserByEmail;
        this.getUserById = getUserById;
        this.getUserByUsername = getUserByUsername;
        this.resetPassword = resetPassword;
        this.updateUser = updateUser;
    }

    @Operation(summary = "Get all the users", description = "Returns a List with all the users registered",
            tags = {"User"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(){
        return listUsers.listUsers().stream()
                .map(u -> new UserDto(u.getId(), u.getUsername(),u.getEmail(), u.getFirstName(), u.getLastName()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a user by a id", description = "Passing the user's id, you receive the information about the user",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/User200Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUserById(@PathVariable ( value = "id") Long id) {
        ResponseEntity<User> userRequested = getUserById.getUserById(id);

        if(userRequested.hasBody()){
            UserDto userDto = new UserDto(Objects.requireNonNull(
                    userRequested.getBody()).getId(),
                    userRequested.getBody().getUsername(),
                    userRequested.getBody().getEmail(),
                    userRequested.getBody().getFirstName(),
                    userRequested.getBody().getLastName());

            return ResponseEntity.ok(userDto);
        }

        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Get a user by a username", description = "Passing the user's username, you receive the information about the user",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/User200Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable ( value = "username") String username) {
        ResponseEntity<User> userRequested = getUserByUsername.getUserByUsername(username);

        if(userRequested.hasBody()){
            UserDto userDto = new UserDto(Objects.requireNonNull(
                    userRequested.getBody()).getId(),
                    userRequested.getBody().getUsername(),
                    userRequested.getBody().getEmail(),
                    userRequested.getBody().getFirstName(),
                    userRequested.getBody().getLastName());

            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email")
    @Operation(summary = "Get a user by a Email", description = "Passing the user's email, you receive the information about the user",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/User200Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUserByEmail(UserEmailDto userEmailDto) {
        ResponseEntity<User> userRequested = getUserByEmail.getUserByEmail(userEmailDto.email());

        if(userRequested.hasBody()){
            UserDto userDto = new UserDto(Objects.requireNonNull(
                    userRequested.getBody()).getId(),
                    userRequested.getBody().getUsername(),
                    userRequested.getBody().getEmail(),
                    userRequested.getBody().getFirstName(),
                    userRequested.getBody().getLastName());

            return ResponseEntity.ok(userDto);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Register a user", description = "Register a user to be able to login",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "201", ref = "#/components/responses/User201Response")
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserRegistrationDto userRegistrationDto){
        User save = createUser.createUser(new User(
                userRegistrationDto.username(),
                userRegistrationDto.email(),
                userRegistrationDto.firstName(),
                userRegistrationDto.lastName(),
                userRegistrationDto.password()
        ));

        return new UserDto(save.getId(), save.getUsername(),save.getEmail(), save.getFirstName(), save.getLastName());
    }
    @Operation(
            summary = "Update a user", description = "Passing a User object, with a new password, you update the password of user's login",
            tags = {"User"}
    )
    @PostMapping("/request-reset-password")
    @ResponseStatus(HttpStatus.CREATED)
    public RequestResetPasswordDto requestResetPassword(@RequestBody UserEmailDto userEmailDto){
        var user = getUserByEmail.getUserByEmail(userEmailDto.email()).getBody();

        return new RequestResetPasswordDto(user.getId(),TokenContext.getToken(), "Link ainda a ser definido");
    }



    @Operation(summary = "Update a user", description = "Passing a User object, with a new password, you update the password of user's login",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/User201Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity confirmResetPassword(@RequestBody UserChangePassword userChangePassword){
        User userToUpdate = new User(userChangePassword.id(), userChangePassword.newPassword());

        ResponseEntity<User> userUpdated = resetPassword.resetPassword(userToUpdate);

        if(userUpdated.getStatusCode().value() == 404) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userUpdated);
    }

    @Operation(summary = "Update a user", description = "Passing a User object, with a new password, you update the password of user's login",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/User201Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserToUpdateDto userToUpdateDto){

        ResponseEntity<User> userUpdated = updateUser.updateUser(id, userToUpdateDto.username(), userToUpdateDto.email(), userToUpdateDto.firstName(), userToUpdateDto.lastName(), userToUpdateDto.password());

        if(userUpdated.getStatusCode().value() == 404) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userUpdated);
    }

    @Operation(summary = "Delete a user by a Id", description = "Passing a Id, the user related to that Id will be deleted",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "204", ref = "#/components/responses/204Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteUser(@PathVariable ( value = "id") Long id) {
        return deleteUser.deleteUser(id);
    }
}
