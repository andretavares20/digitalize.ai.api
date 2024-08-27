package br.com.digitalizeai.api.infra.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.digitalizeai.api.domain.User;
import br.com.digitalizeai.api.domain.ports.repositories.UserRepositoryPort;

@Component
public class UserRepository implements UserRepositoryPort {
    
    private final SpringUserRepository springUserRepository;

    public UserRepository(SpringUserRepository springUserRepository) {
        this.springUserRepository = springUserRepository;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<User> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
