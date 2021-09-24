package com.veterinaria.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Medico;
import com.veterinaria.entity.Paciente;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaPersistence {
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
            mapper.writeValue(new File("proprietarios.json"), agendaDeConsultas);
        }catch (IOException e){
            e.printStackTrace();
        }
        return consulta;

        /*
             * metodo mapea o objeto e
             * localiza no arquivo de cadastro
         */

    }



}
