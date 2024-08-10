package br.com.locationManagementSystem.api.infra.controller.Dtos.User;

public record RequestResetPasswordDto(Long id, String token, String link) {
}
