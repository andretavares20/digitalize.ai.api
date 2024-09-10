package br.com.digitalizeai.api.infra.configs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.digitalizeai.api.domain.User;
import br.com.digitalizeai.api.domain.enums.RoleEnum;
import br.com.digitalizeai.api.infra.adapters.repositories.UserRepository;

@Configuration
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            // Verifica se o usuário admin já existe
            if (userRepository.findByEmail("admin@test.com").isEmpty()) {
                // Cria o usuário admin
                User admin = new User();
                admin.setEmail("admin@test.com");
                admin.setPassword(passwordEncoder.encode("admin")); // Senha criptografada
                admin.setRole(RoleEnum.ADMIN);  // Define o papel de administrador
                admin.setName("admin");
                admin.setUsername("admin");
                userRepository.save(admin);  // Salva o usuário no banco de dados

                System.out.println("Admin user created: admin@test.com / admin");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}
