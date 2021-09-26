package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.PacientePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

        public Paciente altera(Paciente paciente) {
            if (paciente.getProprietario() != null) {
                pacientePersistence.altera(paciente);
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
