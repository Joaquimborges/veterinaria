package com.veterinaria.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Pessoa {

    private String nome;
    private String sobrenome;
    private String cpf;

    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                ", nome: '" + nome + '\n' +
                ", sobrenome: '" + sobrenome + '\n' +
                ", cpf: '" + cpf + '\'' +
                '}';
    }
}
