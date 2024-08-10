package br.com.pavscan.api.infra.controller.Dtos.User;

public record UserRegistrationDto(String username, String email, String firstName, String lastName, String password){
}
