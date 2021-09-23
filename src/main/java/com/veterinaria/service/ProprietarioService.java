package com.veterinaria.service;


import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ProprietarioPersistence;

public class ProprietarioService {

    private final ProprietarioPersistence persistence = new ProprietarioPersistence();

    private boolean cpfNaoUtilizado(String cpf){
        for (Proprietario proprietario : persistence.listarProprietarios()){
            if (proprietario.getCpf().equals(cpf)){
                return false;
            }
        }
        return true;
    }

}
