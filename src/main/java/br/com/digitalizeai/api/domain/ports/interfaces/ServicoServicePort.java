package br.com.digitalizeai.api.domain.ports.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.digitalizeai.api.domain.Servico;

public interface ServicoServicePort {
    List<Servico> getAllServicos();
    Servico getServicoById(Long id);
    Servico createServico(Servico servico);
    Servico updateServico(Long id, Servico servicoDetails);
    void deleteServico(Long id);

}
