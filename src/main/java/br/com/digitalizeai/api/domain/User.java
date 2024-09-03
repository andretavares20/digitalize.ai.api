package br.com.digitalizeai.api.domain;

import br.com.digitalizeai.api.domain.enums.RoleEnum;

public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private RoleEnum role;
    private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public User() {
    }

   

    public User(Long id, String name, String username, String password, String email, RoleEnum role, String telephone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // TODO: construtor com userDto
    // TODO: funções ex: atualizarEstoque
    // TODO: toUserDto

}
