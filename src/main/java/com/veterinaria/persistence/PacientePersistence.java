package com.veterinaria.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.veterinaria.entity.Paciente;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PacientePersistence {
    final String arquivo = "pacientes.json";

    ObjectMapper mapper = new ObjectMapper();
    private List<Paciente> pacientes = new ArrayList<>();

    private void mapearObjeto(){
        mapper.findAndRegisterModules();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public Paciente cadastrar(Paciente paciente){
        mapearObjeto();
        pacientes.add(paciente);
        try {
            mapper.writeValue(new File(arquivo), pacientes);
        }catch (IOException e){
            e.printStackTrace();
        }
        return paciente;
    }


    public Paciente obterPaciente(String nome, String proprietarioCpf) {
        mapearObjeto();
        pacientes = listarPacientes();
        Optional<Paciente> optionalPaciente =
                pacientes.stream()
                        .filter(p -> p.getNome().equals(nome) &&
                                p.getProprietario().getCpf().equals(proprietarioCpf)).findFirst();
        return optionalPaciente.orElse(null);
    }

    public List<Paciente> listarPacientes(){
        mapearObjeto();
        try {
            pacientes = mapper.readValue(new File(arquivo), new TypeReference<>() {
            });
        }catch (IOException e){
            e.printStackTrace();
        }
        return pacientes;
    }

    public Paciente altera(Paciente paciente){
        mapearObjeto();
        pacientes = listarPacientes();
        for (int i = 0; i < pacientes.size(); i++){
            Paciente p = pacientes.get(i);
            if (p.getNome().equals(paciente.getNome()) && p.getSexo().equals(paciente.getSexo()) && p.getRaca().equals(paciente.getRaca()) ){
                pacientes.set(i, paciente);

                try {
                    mapper.writeValue(new File(arquivo), pacientes);
                }catch (IOException e){
                    e.printStackTrace();
                }
                return paciente;
            }
        }
        return null;
    }



    public boolean remove(String nome, String proprietarioCpf){
        mapearObjeto();
        listarPacientes().removeIf(
                paciente -> paciente.getNome().equals(nome) && paciente.getProprietario().getCpf().equals(proprietarioCpf)
        );

        try{
            mapper.writeValue(new File(arquivo), pacientes);
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

}
