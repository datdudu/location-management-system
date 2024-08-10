package br.com.pavscan.api.domain.entities.user;

public class UserFactory {
    private User user;

    public User withLoginPassword(String username,String email, String firstName, String lastName, String password){
        this.user = new User(username, email, firstName, lastName, password);

        return this.user;
    }
}
