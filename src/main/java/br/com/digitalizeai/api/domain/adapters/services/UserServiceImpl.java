package br.com.digitalizeai.api.domain.adapters.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.digitalizeai.api.domain.User;
import br.com.digitalizeai.api.domain.enums.RoleEnum;
import br.com.digitalizeai.api.domain.ports.interfaces.UserServicePort;
import br.com.digitalizeai.api.domain.ports.repositories.UserRepositoryPort;

@Service
@Primary
public class UserServiceImpl implements UserServicePort, UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<br.com.digitalizeai.api.domain.User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

    @Override
    public Optional<br.com.digitalizeai.api.domain.User> getUserById(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public br.com.digitalizeai.api.domain.User createUser(br.com.digitalizeai.api.domain.User user) {
        user.setRole(RoleEnum.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografa a senha antes de salvar
        return userRepositoryPort.save(user);
    }

    @Override
    public br.com.digitalizeai.api.domain.User updateUser(Long id, br.com.digitalizeai.api.domain.User userDetails) {
        Optional<br.com.digitalizeai.api.domain.User> existingUser = userRepositoryPort.findById(id);

        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found with id " + id);
        }

        br.com.digitalizeai.api.domain.User userToUpdate = existingUser.get();
        userToUpdate.setUsername(userDetails.getUsername());
        userToUpdate.setPassword(passwordEncoder.encode(userDetails.getPassword())); // Criptografa a senha antes de
                                                                                     // atualizar
        userToUpdate.setEmail(userDetails.getEmail());
        userToUpdate.setRole(userDetails.getRole());

        return userRepositoryPort.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        userRepositoryPort.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            
            Optional<User> optionalUser = userRepositoryPort.findByEmail(email);
            if(!optionalUser.isPresent()){
                throw new InternalError("Não encontrou usuario pelo email - loadUserByUsername");
            }
            User user = optionalUser.get();
            return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepositoryPort.findByUsername(username);
    }

    @Override
    public Boolean isEmailAvailable(String email) {
        return !userRepositoryPort.findByEmail(email).isPresent();
    }

    @Override
    public Boolean isTelephoneAvailable(String telephone) {
        return userRepositoryPort.isTelephoneAvailable(telephone);
    }
}
