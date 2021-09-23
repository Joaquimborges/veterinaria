package com.veterinaria.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Medico extends Pessoa {

    private Integer numeroRegistro;
    private String especialidade;

    public Medico() {
    }

    public Medico(String nome, String sobrenome, String cpf, Integer numeroRegistro, String especialidade) {
        super(nome, sobrenome, cpf);
        this.numeroRegistro = numeroRegistro;
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "numeroRegistro=" + numeroRegistro +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
