package com.veterinaria.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class Paciente {

    private final String numeroDoPaciente;
    private String especie;
    private String cor;
    private String raca;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private Proprietario proprietario;



    public String setNumeroDoPaciente() {
        return UUID.randomUUID().toString();

    }



    public Paciente(String especie, String cor, String raca, String nome, String sexo, LocalDate dataNascimento, Proprietario proprietario) {
        this.especie = especie;
        this.cor = cor;
        this.raca = raca;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.proprietario = proprietario;
        numeroDoPaciente = setNumeroDoPaciente();

    }

    @Override
    public String toString() {
        return "Paciente{" + '\n' +
                ", numeroDoPaciente: '" + numeroDoPaciente + '\n' +
                ", especie:'" + especie + '\n' +
                ", cor: '" + cor + '\n' +
                ", raca: '" + raca + '\n' +
                ", nome: '" + nome + '\n' +
                ", sexo: '" + sexo + '\n' +
                ", dataNascimento: " + dataNascimento + '\n' +
                ", proprietario: " + proprietario + '\'' +
                '}';
    }

}
