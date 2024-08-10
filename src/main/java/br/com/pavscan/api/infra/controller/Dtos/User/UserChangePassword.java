package br.com.pavscan.api.infra.controller.Dtos.User;

public record UserChangePassword(Long id, String token, String newPassword) {
}
