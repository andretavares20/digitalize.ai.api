package br.com.digitalizeai.api.infra.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalizeai.api.infra.adapters.entities.UserEntity;

@Repository
public interface SpringUserRepository extends JpaRepository<UserEntity,Long>{
    
}
