package br.com.digitalizeai.api.domain.ports.repositories;

import java.util.List;

import br.com.digitalizeai.api.domain.Servico;

public interface ServicoRepositoryPort {
    List<Servico> findAll();
    Servico findById(Long id);
    Servico save(Servico servico);
    void deleteById(long id);
}
