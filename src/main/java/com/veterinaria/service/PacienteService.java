package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.PacientePersistence;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

    public class PacienteService {

        @Autowired
        PacientePersistence pacientePersistence;
        ConsultaPersistence consultaPersistence;

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


    }
