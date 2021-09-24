package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.persistence.ConsultaPersistence;

public class ConsultaService {

    private final ConsultaPersistence consultaPersistence = new ConsultaPersistence();

    public void agendarConsulta(Consulta consulta) {
        consultaPersistence.cadastrarConsulta(consulta);
    }


}
