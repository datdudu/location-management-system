package br.com.locationManagementSystem.api.infra.controller.Dtos.User;

public record UserToUpdateDto (String username, String email, String firstName, String lastName, String password){
}
