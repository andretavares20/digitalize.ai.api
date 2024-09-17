package br.com.digitalizeai.api.domain.adapters.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.digitalizeai.api.domain.Servico;
import br.com.digitalizeai.api.domain.ports.interfaces.ServicoServicePort;
import br.com.digitalizeai.api.domain.ports.repositories.ServicoRepositoryPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoServiceImpl implements ServicoServicePort {

    private final ServicoRepositoryPort servicoRepositoryPort;

    @Override
    public List<Servico> getAllServicos() {
        return servicoRepositoryPort.findAll();
    }

    @Override
    public Servico getServicoById(Long id) {
        return servicoRepositoryPort.findById(id);
    }

    @Override
    public Servico createServico(Servico servico) {
        return servicoRepositoryPort.save(servico);
    }

    @Override
    public Servico updateServico(Long id, Servico servicoDetails) {
        Servico servico = servicoRepositoryPort.findById(id);

        if(servico==null){
            throw new InternalError("Servico de id " + id + "não encontrado");
        }

        servico.setName(servicoDetails.getName());
        servico.setPrice(servicoDetails.getPrice());

        return servicoRepositoryPort.save(servico);
    }

    @Override
    public void deleteServico(Long id) {
        servicoRepositoryPort.deleteById(id);
    }

}
