package br.com.digitalizeai.api.application.adapters.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalizeai.api.domain.User;
import br.com.digitalizeai.api.domain.ports.interfaces.UserServicePort;

@RestController
@RequestMapping("/admin/users")
public class UserAdminController {

    private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class); // Logger

    private final UserServicePort userServicePort;

    public UserAdminController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @GetMapping
    public List<User> getAllUsers() {
        logger.debug("Acessando endpoint GET /users");
        return userServicePort.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.debug("Acessando endpoint GET /users/{}", id);
        Optional<User> user = userServicePort.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Usuário com ID {} não encontrado", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        logger.debug("Atualizando usuário com ID {}", id);
        User updatedUser = userServicePort.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.debug("Deletando usuário com ID {}", id);
        userServicePort.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
