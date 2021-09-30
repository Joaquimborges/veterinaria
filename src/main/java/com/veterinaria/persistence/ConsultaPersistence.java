package com.veterinaria.persistence;




import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.veterinaria.entity.Consulta;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConsultaPersistence {
    final String arquivo = "agendaDeConultas.json";
    ObjectMapper mapper = new ObjectMapper();


    private List<Consulta> agendaDeConsultas = new ArrayList<>();

    private void mapearObjeto(){
        mapper.findAndRegisterModules();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        /*
         * metodo adiciona o objeto na lista
         * e salva em um arquivo json.
         */
    }


    public Consulta cadastrarConsulta(Consulta consulta) {
        mapearObjeto();
        agendaDeConsultas.add(consulta);

        try {
            mapper.writeValue(new File(arquivo), agendaDeConsultas);
        }catch (IOException e){
            e.printStackTrace();
        }
        return consulta;
    }



    public List<Consulta> listar() {
        mapearObjeto();
        try {
            agendaDeConsultas = mapper.readValue(new File(arquivo), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agendaDeConsultas;


    }

    public Consulta alterar(Consulta consulta){
        mapearObjeto();
        agendaDeConsultas = listar();
        for (int i =0; i < agendaDeConsultas.size(); i++){
            Consulta consul = agendaDeConsultas.get(i);
            if (consul.getPaciente().equals(consulta.getPaciente())){
                agendaDeConsultas.set(i, consulta);
                try {
                    mapper.writeValue(new File(arquivo), agendaDeConsultas);
                }catch (IOException e){
                    e.printStackTrace();
                }
                return consulta;
            }
        }
        return null;

    }


}
