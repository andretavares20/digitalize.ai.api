package br.com.digitalizeai.api.infra.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalizeai.api.infra.adapters.entities.ServicoEntity;

@Repository
public interface SpringServicoRepository extends JpaRepository<ServicoEntity, Long> {
}
