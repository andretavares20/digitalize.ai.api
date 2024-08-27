package br.com.digitalizeai.api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.digitalizeai.api.domain.adapters.services.UserServiceImpl;
import br.com.digitalizeai.api.domain.ports.interfaces.UserServicePort;
import br.com.digitalizeai.api.domain.ports.repositories.UserRepositoryPort;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public UserServicePort userServicePort(UserRepositoryPort userRepositoryPort) {
        return new UserServiceImpl(userRepositoryPort);
    }

    
}
