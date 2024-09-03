package br.com.digitalizeai.api.infra.adapters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalizeai.api.infra.adapters.entities.UserEntity;

@Repository
public interface SpringUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username); // Adicionando o método findByUsername
    Optional<UserEntity> findByEmail(String email); // Adicionando o método findByUsername
    Boolean existsByTelephone(String telephone); // Novo método
}
