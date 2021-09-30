package com.veterinaria.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class Consulta {

    private String motivo;
    private String diagnosticoPossivel;
    private String tratamentoSeguido;
    private LocalDate dataDia;
    private LocalTime hora;
    private Paciente paciente;
    private Medico medicoVeterinario;


    public Consulta(String motivo, String diagnosticoPossivel, String tratamentoSeguido, LocalTime hora, Paciente paciente, Medico medico) {
        this.motivo = motivo;
        this.diagnosticoPossivel = diagnosticoPossivel;
        this.tratamentoSeguido = tratamentoSeguido;
        this.dataDia = dataDia;
        this.hora = hora;
        this.paciente = paciente;
        this.medicoVeterinario = medico;
    }

    @Override
    public String toString() {
        return "Consulta{" + '\n' +
                ", Motivo: '" + motivo + '\n' +
                ", Diagnostico: '" + diagnosticoPossivel + '\n' +
                ", Tratamento a ser seguido: '" + tratamentoSeguido + '\n' +
                ", Data e hora de atendimento: " + dataDia + '\n' +
                ", Hora: " + hora + '\n' +
                ", Paciente: " + paciente + '\n' +
                ", Médico Veterinário: " + medicoVeterinario + '\'' +
                '}';
    }
}
