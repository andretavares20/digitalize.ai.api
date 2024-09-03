package br.com.digitalizeai.api.infra.adapters.entities;

import br.com.digitalizeai.api.domain.User;
import br.com.digitalizeai.api.domain.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;          // Novo campo para o nome do usuário
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    private String telephone;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();                // Mapeando o nome do usuário
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.telephone = user.getTelephone();
    }

    public UserEntity() {
    }

    public User toUser() {
        return new User(this.id, this.name, this.username, this.password, this.email, this.role, this.telephone);
    }


}
