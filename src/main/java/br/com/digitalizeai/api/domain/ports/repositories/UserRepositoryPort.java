package br.com.digitalizeai.api.domain.ports.repositories;

import java.util.List;
import java.util.Optional;

import br.com.digitalizeai.api.domain.User;

public interface UserRepositoryPort {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username); // Adicione este método
    Optional<User> findByEmail(String email); // Adicione este método
    User save(User user);
    void deleteById(Long id);
    Boolean isTelephoneAvailable(String telephone);
}
