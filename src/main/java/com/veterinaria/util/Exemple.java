package com.veterinaria.util;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Medico;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.service.ConsultaService;
import com.veterinaria.service.PacienteService;

import java.time.LocalDate;
import java.time.LocalTime;

public class Exemple {
    public static void main(String[] args) {

        ConsultaService cs = new ConsultaService();
        PacienteService ps = new PacienteService();
        LocalDate data = LocalDate.of(2021, 9, 25);
        LocalDate data2 = LocalDate.of(2021, 9, 20);
        LocalTime hora = LocalTime.of(10, 15);
        LocalTime hora2 = LocalTime.of(11, 15);

        Proprietario proprietario1 = new Proprietario("Augusto", "Sousa", "11223344556",
                "Rua 2", "91276338902", data);
        Proprietario proprietario2 = new Proprietario("Paulo", "Sousa", "11223344556",
                "Rua 2", "91276338902", data);

        Paciente paciente = new Paciente("cachorro", "Branco", "Akita",
                "Tobirama", "Macho", data, proprietario1);
        Paciente paciente2 = new Paciente("cachorro", "Branco", "Akita",
                "Tobirama", "Macho", data, proprietario1);
        Paciente paciente3 = new Paciente("cachorro", "Branco", "Akita",
                "Tobirama", "Macho", data, proprietario2);
        Paciente paciente4 = new Paciente("cachorro", "Branco", "Akita",
                "Tobirama", "Macho", data, proprietario2);

        Medico medico = new Medico("Pedro", "Paulo", "00228811763", 7711663,
                "veterinario");

        Consulta consulta1 = new Consulta("Dor de cabeca", "Dor",
                "cuidar mais", data, hora,  paciente, medico);

        Consulta consulta2 = new Consulta("Dor de barriga", "Dor",
                "cuidar mais", data, hora,  paciente, medico);

        Consulta consulta3 = new Consulta("Dor de barriga", "Dor",
                "cuidar mais", data2, hora,  paciente, medico);

       // cs.agendarConsulta(consulta1);
       // cs.agendarConsulta(consulta2);
       // cs.agendarConsulta(consulta3);

        System.out.println(cs.consultasPaciente("Tobirama", "11223344556"));
       // System.out.println(cs.totalConsultasMedico(7711663));

       // System.out.println(cs.consultasMesmoDia(data, "Tobirama", "11223344556"));

//        ps.cadastraPaciente(paciente);
//        ps.cadastraPaciente(paciente2);
//        ps.cadastraPaciente(paciente3);
//        ps.cadastraPaciente(paciente4);
       // System.out.println(ps.listarPacientes());
    }
}
