package br.com.digitalizeai.api.infra.adapters.entities;

import br.com.digitalizeai.api.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

    public UserEntity(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public UserEntity() {
    }

    // TODO: funções ex: atualizar

    public User toUser() {
        return new User(this.id, this.username, this.password, this.email, this.role);
    }

}
