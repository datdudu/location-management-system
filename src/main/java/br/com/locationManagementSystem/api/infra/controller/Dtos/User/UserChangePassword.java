package br.com.locationManagementSystem.api.infra.controller.Dtos.User;

public record UserChangePassword(Long id, String token, String newPassword) {
}
