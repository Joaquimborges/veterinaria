package com.veterinaria.persistence;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.veterinaria.entity.Medico;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Setter
public class MedicoPersistence {

    ObjectMapper mapper;

    @Autowired
    private List<Medico> medicos;


    private void mapearObjeto(){
        mapper.findAndRegisterModules();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * metodo adiciona o objeto na lista
     * e salva em um arquivo json.
     */

    public Medico cadastrar(Medico medico){
        mapearObjeto();
        medicos.add(medico);
        try {
            mapper.writeValue(new File("medicos.json"), medicos);
        }catch (IOException e){
            e.printStackTrace();
        }
        return medico;
    }

    /**
     * metodo mapea o objeto e
     * localiza no arquivo de cadastro
     *
     * Médico: String do Integer CRVET
     */
    public Medico obterUm(Integer crvet){
        mapearObjeto();

        return medicos.stream()
                .filter(m->m.getNumeroRegistro()
                        .equals(crvet))
                .findFirst()
                .orElse(null);

    }

    /**
     * metodo faz a leitura de todos os objetos da lista
     */
    public List<Medico> listarMedicos(){
        mapearObjeto();
        try {
            medicos = mapper.readValue(new File("medicos.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    /**
     * metodo percorre a listagem, compara os objetos,
     * atualiza os dados e reescreve no arquivo.
     */
    public Medico altera(Medico medico)
    {
        mapearObjeto();

        Integer id;

        Optional<Medico> m = listarMedicos()
                .stream()
                .filter(x-> x.getNumeroRegistro().equals(medico.getNumeroRegistro()))
                .findFirst();

        if (m == null) return null;

        id = listarMedicos().indexOf(m);

        medicos.set(id, medico);

        try {
            mapper.writeValue(new File("medicos.json"), medicos);
        }catch (IOException e){
            e.printStackTrace();
        }
        return medico;

    }


    public boolean remove(Integer crvet){
        mapearObjeto();
        Boolean removeu = listarMedicos().removeIf(x -> x.getNumeroRegistro().equals(crvet));

        if (removeu)
        {
            try {
                mapper.writeValue(new File("medico.json"), medicos);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return removeu;
    }




}
