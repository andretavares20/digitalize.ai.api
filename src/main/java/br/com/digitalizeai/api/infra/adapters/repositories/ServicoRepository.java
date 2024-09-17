package br.com.digitalizeai.api.infra.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import br.com.digitalizeai.api.domain.Servico;
import br.com.digitalizeai.api.domain.ports.repositories.ServicoRepositoryPort;
import br.com.digitalizeai.api.infra.adapters.entities.ServicoEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServicoRepository implements ServicoRepositoryPort{

    private final SpringServicoRepository springServicoRepository;

    @Override
    public List<Servico> findAll() {
        return springServicoRepository.findAll().stream().map(ServicoEntity::toServico).toList();
    }

    @Override
    public Servico findById(Long id) {

        Optional<ServicoEntity> optionalServicoEntity = springServicoRepository.findById(id);
        if(!optionalServicoEntity.isPresent()){
            throw new InternalError("Serviço de id " + id + "não existe");
        }
        return optionalServicoEntity.get().toServico();
    }

    @Override
    public Servico save(Servico servico) {
        return springServicoRepository.save(new ServicoEntity(servico)).toServico();
    }

    @Override
    public void deleteById(long id) {
        springServicoRepository.deleteById(id);
    }
    
}
