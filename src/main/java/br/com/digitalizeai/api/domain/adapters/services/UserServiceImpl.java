package br.com.digitalizeai.api.domain.adapters.services;

import java.util.List;
import java.util.Optional;

import br.com.digitalizeai.api.domain.User;
import br.com.digitalizeai.api.domain.ports.interfaces.UserServicePort;
import br.com.digitalizeai.api.domain.ports.repositories.UserRepositoryPort;

public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        Optional<User> existingUser = userRepositoryPort.findById(id);

        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found with id " + id);
        }

        User userToUpdate = existingUser.get();
        userToUpdate.setUsername(userDetails.getUsername());
        userToUpdate.setPassword(userDetails.getPassword());
        userToUpdate.setEmail(userDetails.getEmail());
        userToUpdate.setRole(userDetails.getRole());

        return userRepositoryPort.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        userRepositoryPort.deleteById(id);
    }
}
