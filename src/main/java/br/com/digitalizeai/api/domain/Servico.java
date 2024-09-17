package br.com.digitalizeai.api.domain;

import lombok.Data;

@Data
public class Servico {

    private Long id;

    private String name;
    private Double price;
    public Servico(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    

}
