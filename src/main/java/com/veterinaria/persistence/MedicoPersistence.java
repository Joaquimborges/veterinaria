package com.veterinaria.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.veterinaria.entity.Medico;
import com.veterinaria.entity.Proprietario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicoPersistence {


    ObjectMapper mapper = new ObjectMapper();

    //pra quê a lista? pegar os json da persistência e reescrever, né?
    private List<Medico> medicos = new ArrayList<>();


    private void mapearObjeto(){
        mapper.findAndRegisterModules();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


    private boolean cpfNaoUtilizado(String cpf){

        //iterar
        for (Medico medico : listarMedicos()){
            if (medico.getCpf().equals(cpf)){
                return false;
            }
        }
        return true;
    }

    private boolean crmNaoUtilizado(String crm){

        //iterar
        for (Medico medico : listarMedicos()){
            if (medico.getCpf().equals(crm)){
                return false;
            }
        }
        return true;
    }

    private boolean cpfOuCrNaoUtilizado(String cpf, Integer crm)
    {
        boolean jaCadastrado
                =  listarMedicos().stream()
                .filter(x-> x.getCpf().equals(cpf) || x.getNumeroRegistro().equals(crm))
                .count() == 0;

        return jaCadastrado;
    }

    /**
     * metodo adiciona o objeto na lista
     * e salva em um arquivo json.
     */

    public Medico cadastrar(Medico medico){
        if (cpfOuCrNaoUtilizado(medico.getCpf(), medico.getNumeroRegistro())){
            mapearObjeto();
            medicos.add(medico);
            try {
                mapper.writeValue(new File("medicos.json"), medicos);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return medico;
    }

    /**
     * metodo mapea o objeto e
     * localiza no arquivo de cadastro
     */
//    public Medico obterUm(String cpf){
//        mapearObjeto();
//        medicos = listarMedicos();
//        Optional<Medico> optionalMedico = medicos.stream()
//                .filter(p -> p.getCpf().equals(cpf)).findFirst();
//        return optionalMedico.orElse(null);
//    }

    public Medico obterUm(Integer numRegistro){
        mapearObjeto();
        medicos = listarMedicos();
        Optional<Medico> optionalMedico = medicos.stream()
                .filter(p -> p.getCpf().equals(numRegistro)).findFirst();
        return optionalMedico.orElse(null);
    }




    /**
     * metodo faz a leitura de todos os objetos da lista
     */
    public List<Medico> listarMedicos(){
        mapearObjeto();
        try {
            medicos = mapper.readValue(new File("medicos.json"), new TypeReference<List<Medico>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    public Medico delete(Integer crm)
    {
        Medico m = obterUm(crm);
        medicos.remove(m);
        try {
            mapper.writeValue(new File("medicos.json"), medicos);
        }catch (IOException e){
            e.printStackTrace();
        }

        return m;

    }





}
