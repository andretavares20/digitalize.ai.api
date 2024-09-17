package br.com.digitalizeai.api.application.adapters.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalizeai.api.domain.Servico;
import br.com.digitalizeai.api.domain.ports.interfaces.ServicoServicePort;

@RestController
@RequestMapping("/admin/servicos")
@PreAuthorize("hasRole('ADMIN')")  // Garante que apenas administradores podem acessar este controller
public class ServicoAdminController {

    private final ServicoServicePort servicoServicePort;

    public ServicoAdminController(ServicoServicePort servicoServicepPort) {
        this.servicoServicePort = servicoServicepPort;
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

    @PostMapping
    public ResponseEntity<Servico> createServico(@RequestBody Servico servico) {
        Servico newServico = servicoServicePort.createServico(servico);
        return ResponseEntity.ok(newServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> updateServico(@PathVariable Long id, @RequestBody Servico servicoDetails) {
        Servico updatedServico = servicoServicePort.updateServico(id, servicoDetails);
        return ResponseEntity.ok(updatedServico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        servicoServicePort.deleteServico(id);
        return ResponseEntity.noContent().build();
    }
}
