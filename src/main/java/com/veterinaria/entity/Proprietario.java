package com.veterinaria.entity;

import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Setter
public class Proprietario extends Pessoa {

    private String endereco;
    private String telefoneContato;
    private LocalDate dataDeNascimento;

    public String getEndereco() {
        return endereco;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public LocalDate getDataDeNascimento() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataDeNascimento;
    }

    public Proprietario(String nome, String sobrenome, String cpf, String endereco, String telefoneContato, LocalDate dataDeNascimento) {
        super(nome, sobrenome, cpf);
        this.endereco = endereco;
        this.telefoneContato = telefoneContato;
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public String toString() {
        return "Proprietario{" +
                "endereco='" + endereco + '\'' +
                ", telefoneContato='" + telefoneContato + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                '}';
    }
}
