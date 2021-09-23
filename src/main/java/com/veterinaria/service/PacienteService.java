package com.veterinaria.service;


import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ProprietarioPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacientePersistence persistence = new PacientePersistence();



    public Paciente cadastrar (Paciente paciente){

            try {
                persistence.cadastrar(proprietario);
                return proprietario;
            }catch (RuntimeException e){
                e.printStackTrace();
            }

        return null;
    }


    public Proprietario getProprietario(String cpf){
        return persistence.obterUm(cpf);
    }


    public List<Proprietario> Listar(){
        return persistence.listarProprietarios();
    }


    public Proprietario altera(Proprietario proprietario){
        if (proprietario != null){
            return persistence.altera(proprietario);
        }
        return null;
    }



}
