package br.com.digitalizeai.api.domain.dtos;

public class UserDTO {

    private Long id;
    private String name;         // Novo campo para o nome do usuário
    private String username;
    private String password;
    private String email;
    private String role;
    private String telephone;    // Novo campo para o telefone do usuário

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // Construtor

    public UserDTO(Long id, String name, String username, String password, String email, String role, String telephone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.telephone = telephone;
    }

}
