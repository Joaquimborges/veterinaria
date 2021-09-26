package com.veterinaria.dto.paciente;

import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class PacienteDTO {

    private String numeroDoPaciente;
    private String especie;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private Proprietario proprietario;

    public String getNumeroDoPaciente() {
        numeroDoPaciente = UUID.randomUUID().toString();
        return numeroDoPaciente;
    }

    public PacienteDTO() {
    }

    public PacienteDTO(String numeroDoPaciente, String nome, String especie, String sexo, LocalDate dataNascimento, Proprietario proprietario) {
        this.numeroDoPaciente = numeroDoPaciente;
        this.especie = especie;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.proprietario = proprietario;
    }

    public static PacienteDTO converter(Paciente paciente){
        return new PacienteDTO(paciente.getNumeroDoPaciente(), paciente.getNome(),
                paciente.getEspecie(), paciente.getSexo(), paciente.getDataNascimento(), paciente.getProprietario());
    }

    public static List<PacienteDTO> coverterLista(List<Paciente> pacienteList){
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();
        for (Paciente paciente : pacienteList){
            pacienteDTOList.add(new PacienteDTO(paciente.getNumeroDoPaciente(), paciente.getNome(),
                   paciente.getEspecie(), paciente.getSexo(), paciente.getDataNascimento(), paciente.getProprietario()));
        }
        return pacienteDTOList;
    }
}
