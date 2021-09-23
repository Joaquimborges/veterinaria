package com.veterinaria.persistence;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.veterinaria.entity.Proprietario;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
public class ProprietarioPersistence {

    ObjectMapper mapper = new ObjectMapper();
    private List<Proprietario> proprietarios = new ArrayList<>();


    private void mapearObjeto(){
        mapper.findAndRegisterModules();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private boolean cpfNaoUtilizado(String cpf){
        for (Proprietario proprietario : listarProprietarios()){
            if (proprietario.getCpf().equals(cpf)){
                return false;
            }
        }
        return true;
    }

    /**
     * metodo adiciona o objeto na lista
     * e salva em um arquivo json.
     */

    public Proprietario cadastrar(Proprietario proprietario){
        if (cpfNaoUtilizado(proprietario.getCpf())){
            mapearObjeto();
            proprietarios.add(proprietario);
            try {
                mapper.writeValue(new File("proprietarios.json"), proprietarios);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return proprietario;
    }

    /**
     * metodo mapea o objeto e
     * localiza no arquivo de cadastro
     */
    public Proprietario obterUm(String cpf){
        mapearObjeto();
        proprietarios = listarProprietarios();
        Optional<Proprietario> optionalProprietario = proprietarios.stream()
                .filter(p -> p.getCpf().equals(cpf)).findFirst();
        return optionalProprietario.orElse(null);
    }

    /**
     * metodo faz a leitura de todos os objetos da lista
     */
    public List<Proprietario> listarProprietarios(){
        mapearObjeto();
        try {
            proprietarios = mapper.readValue(new File("proprietarios.json"), new TypeReference<List<Proprietario>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proprietarios;
    }







}
