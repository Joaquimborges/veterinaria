package com.veterinaria.dto.paciente;

import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PacienteDtoRequest {

    private String especie;
    private String cor;
    private String raca;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private Proprietario proprietario;



    public PacienteDtoRequest() {
    }

    public PacienteDtoRequest(String especie, String cor, String raca, String nome, String sexo, LocalDate dataNascimento, Proprietario proprietario) {
        this.especie = especie;
        this.cor = cor;
        this.raca = raca;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.proprietario = proprietario;
    }

    public Paciente converte(){
        return new Paciente(especie, cor, raca, nome, sexo, dataNascimento, proprietario);
    }

}
