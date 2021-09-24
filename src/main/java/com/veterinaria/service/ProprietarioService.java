package com.veterinaria.service;


import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ProprietarioPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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


    public Proprietario cadastrar(Proprietario proprietario){
        if (cpfNaoUtilizado(proprietario.getCpf())){
            try {
                persistence.cadastrar(proprietario);
                return proprietario;
            }catch (RuntimeException e){
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("Verifica as informações, CPF em uso!");
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
