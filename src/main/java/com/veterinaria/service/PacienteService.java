package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Paciente;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.PacientePersistence;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;


@Service
    public class PacienteService {

        PacientePersistence pacientePersistence = new PacientePersistence();
        ConsultaPersistence consultaPersistence = new ConsultaPersistence();

        public Paciente cadastraPaciente(Paciente paciente) {

            if (paciente.getProprietario() != null) {

                pacientePersistence.cadastrar(paciente);
                return paciente;
            }

            return null;
        }

        public Paciente obterPaciente(String nome, String proprietarioCpf) {

            return pacientePersistence.obterPaciente(nome, proprietarioCpf);

        }

        public Paciente altera( Paciente paciente) {
            if (paciente.getProprietario() != null) {
                pacientePersistence.altera(paciente);
                //aqui ele nao retornava, nada!
                return paciente;
            }
            return null;
        }

        private boolean pacienteNaoExisteNaConsulta(String cpf) {


            for (Consulta consulta : consultaPersistence.listar()) {
                if (consulta.getPaciente().getProprietario().getCpf().equals(cpf)) {
                    return false;
                }
            }
            return true;
        }

        public boolean apagar(String nome, String cpf) {
            if (pacienteNaoExisteNaConsulta(cpf))
                return pacientePersistence.remove(nome, cpf);
            return false;
        }

        public List<Paciente> listarPacientes(){
         List<Paciente> pacientes =  pacientePersistence.listarPacientes();
         pacientes.sort(Comparator.comparing(paciente -> paciente.getProprietario().getNome()));

             return pacientes;
        }


    }
