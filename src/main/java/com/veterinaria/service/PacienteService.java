package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Paciente;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.PacientePersistence;

import com.veterinaria.persistence.ProprietarioPersistence;

import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;


@Service
public class PacienteService {

    PacientePersistence pacientePersistence = new PacientePersistence();
    ConsultaPersistence consultaPersistence = new ConsultaPersistence();
    ProprietarioPersistence proprietarioPersistence = new ProprietarioPersistence();

    public Paciente cadastraPaciente(Paciente paciente) {

            if (paciente.getProprietario() != null && !proprietarioNaoExiste(paciente.getProprietario().getCpf())) {

                pacientePersistence.cadastrar(paciente);
                return paciente;
            }

            return null;
        }

    private boolean proprietarioNaoExiste(String cpf) {
       for (int i=0;i<proprietarioPersistence.listarProprietarios().size();i++){
           if(proprietarioPersistence.listarProprietarios().get(i).getCpf().equals(cpf)){
               return false;
           }
       }
       return true;
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
