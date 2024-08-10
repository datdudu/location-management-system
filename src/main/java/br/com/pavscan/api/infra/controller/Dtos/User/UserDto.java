package br.com.pavscan.api.infra.controller.Dtos.User;

import br.com.pavscan.api.domain.entities.user.User;

public record UserDto(Long id, String username, String email, String firstName, String lastName) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.id());
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        return user;
    }
}
