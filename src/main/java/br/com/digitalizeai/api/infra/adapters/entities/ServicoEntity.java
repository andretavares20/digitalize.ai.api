package br.com.digitalizeai.api.infra.adapters.entities;

import br.com.digitalizeai.api.domain.Servico;
import br.com.digitalizeai.api.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "servicos")
@Data
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    public ServicoEntity(Servico servico) {
        this.id = servico.getId();
        this.name=servico.getName();
        this.price=servico.getPrice();
    }

    public ServicoEntity() {
    }

    public Servico toServico() {
        return new Servico(this.id, this.name, this.price);
    }


}
