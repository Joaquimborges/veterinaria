package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ConsultaService {

    private final ConsultaPersistence consultaPersistence = new ConsultaPersistence();

    public Consulta agendarConsulta(Consulta consulta) {
        consultaPersistence.cadastrarConsulta(consulta);
        return consulta;
    }

    public Consulta alterar(Consulta consulta){
        if (consulta != null){
            return consultaPersistence.alterar(consulta);
        }
        return null;
    }


    public List<Consulta> consultasPaciente(String nomePaciente, String cpfProprietario){
        List<Consulta> consultas = consultaPersistence.listar();
        consultas .stream()
                  .filter(p -> p.getPaciente().getNome().equals(nomePaciente) &&
                        p.getPaciente().getProprietario().getCpf().equals(cpfProprietario))
                  .sorted(Comparator.comparing(p -> p.getPaciente().getProprietario().getNome()));

        return consultas;
    }



}
