package com.veterinaria.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class Paciente {

    private String numeroDoPaciente;
    private String especie;
    private String cor;
    private String raca;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private Proprietario proprietario;

    public String getNumeroDoPaciente() {
        numeroDoPaciente = UUID.randomUUID().toString();
        return numeroDoPaciente;
    }

    public String getEspecie() {
        return especie;
    }

    public String getCor() {
        return cor;
    }

    public String getRaca() {
        return raca;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Paciente() {
    }

    public Paciente(String especie, String cor, String raca, String nome, String sexo, LocalDate dataNascimento, Proprietario proprietario) {
        this.especie = especie;
        this.cor = cor;
        this.raca = raca;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "numeroDoPaciente='" + numeroDoPaciente + '\'' +
                ", especie='" + especie + '\'' +
                ", cor='" + cor + '\'' +
                ", raca='" + raca + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", proprietario=" + proprietario +
                '}';
    }

}
