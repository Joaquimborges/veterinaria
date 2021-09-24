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

    /**
     * metodo adiciona o objeto na lista
     * e salva em um arquivo json.
     */

    public Proprietario cadastrar(Proprietario proprietario){
            mapearObjeto();
            proprietarios.add(proprietario);
            try {
                mapper.writeValue(new File("proprietarios.json"), proprietarios);
            }catch (IOException e){
                e.printStackTrace();
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
            proprietarios = mapper.readValue(new File("proprietarios.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proprietarios;
    }

    /**
     * metodo percorre a listagem, compara os objetos,
     * atualiza os dados e reescreve no arquivo.
     */
    public Proprietario altera(Proprietario proprietario)
    {
        mapearObjeto();

        Optional<Proprietario> p = listarProprietarios()
                .stream()
                    .filter(x-> x.getCpf().equals(proprietario.getCpf()))
                        .findFirst();

        if (p == null ) return null;

        try {
            mapper.writeValue(new File("proprietarios.json"), proprietarios);
        }catch (IOException e){
            e.printStackTrace();
        }
        return proprietario;

    }


    public boolean remove(String cpf){
        mapearObjeto();
        listarProprietarios().removeIf(proprietario -> proprietario.getCpf().equals(cpf));
        try {
            mapper.writeValue(new File("proprietarios.json"), proprietarios);
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }




}
