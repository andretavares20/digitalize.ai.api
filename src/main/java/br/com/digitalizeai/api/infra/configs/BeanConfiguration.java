package br.com.digitalizeai.api.infra.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.digitalizeai.api.domain.adapters.services.UserServiceImpl;
import br.com.digitalizeai.api.domain.ports.interfaces.UserServicePort;
import br.com.digitalizeai.api.domain.ports.repositories.UserRepositoryPort;

@Configuration
public class BeanConfiguration {

    private final PasswordEncoder passwordEncoder;

    public BeanConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserServicePort userServicePort(UserRepositoryPort userRepositoryPort) {
        return new UserServiceImpl(userRepositoryPort, passwordEncoder);
    }
}
