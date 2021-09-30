package com.veterinaria.persistence;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.veterinaria.entity.Medico;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Setter
public class MedicoPersistence {
    final String arquivo = "medicos.json";

    ObjectMapper mapper = new ObjectMapper();
    private List<Medico> medicos = new ArrayList<>();


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
            mapper.writeValue(new File(arquivo), medicos);
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
            medicos = mapper.readValue(new File(arquivo), new TypeReference<>() {
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
        for (int i = 0; i < listarMedicos().size(); i++){
            Medico medico1 = listarMedicos().get(i);
            if (medico.getNumeroRegistro().equals(medico1.getNumeroRegistro())){
                listarMedicos().set(i, medico);
                try {
                    mapper.writeValue(new File(arquivo), medicos);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return medico;
    }





    public Boolean remove(Integer crvet){
        mapearObjeto();

        listarMedicos().removeIf(medico -> medico.getNumeroRegistro().equals(crvet));
        try {
            mapper.writeValue(new File(arquivo), medicos);
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;

    }




}
