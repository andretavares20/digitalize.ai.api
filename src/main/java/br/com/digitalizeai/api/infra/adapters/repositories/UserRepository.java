package br.com.digitalizeai.api.infra.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.digitalizeai.api.domain.User;
import br.com.digitalizeai.api.domain.ports.repositories.UserRepositoryPort;
import br.com.digitalizeai.api.infra.adapters.entities.UserEntity;

@Component
public class UserRepository implements UserRepositoryPort {
    
    private final SpringUserRepository springUserRepository;

    public UserRepository(SpringUserRepository springUserRepository) {
        this.springUserRepository = springUserRepository;
    }

    @Override
    public List<User> findAll() {
        return springUserRepository.findAll().stream().map(UserEntity::toUser).toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return springUserRepository.findByUsername(username)
                                   .map(UserEntity::toUser); // Convertendo UserEntity para User
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        return springUserRepository.save(userEntity).toUser();
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springUserRepository.findByEmail(email)
                                   .map(UserEntity::toUser); // Convertendo UserEntity para User
    }

    @Override
    public Boolean isTelephoneAvailable(String telephone) {
        return !springUserRepository.existsByTelephone(telephone);
    }

    
}
