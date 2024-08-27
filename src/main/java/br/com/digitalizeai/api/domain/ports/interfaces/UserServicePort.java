package br.com.digitalizeai.api.domain.ports.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.digitalizeai.api.domain.User;

public interface UserServicePort {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}
