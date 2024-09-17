package br.com.digitalizeai.api.application.adapters.controllers.client;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalizeai.api.domain.Servico;
import br.com.digitalizeai.api.domain.ports.interfaces.ServicoServicePort;

@RestController
@RequestMapping("/client/servicos")
public class ServicoClientController {

    private final ServicoServicePort servicoServicePort;

    public ServicoClientController(ServicoServicePort servicoServicePort) {
        this.servicoServicePort = servicoServicePort;
    }

    @GetMapping
    public List<Servico> getAllServicos() {
        return servicoServicePort.getAllServicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServicoById(@PathVariable Long id) {
        Servico servico = servicoServicePort.getServicoById(id);
        return ResponseEntity.ok().body(servico);
    }
}
