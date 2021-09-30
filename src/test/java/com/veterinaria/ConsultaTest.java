package com.veterinaria;

import static org.junit.jupiter.api.Assertions.*;
import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Medico;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.service.ConsultaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

 public class ConsultaTest {

    Proprietario proprietario = new Proprietario("Marcos", "Cruz", "01826478032",
            "antonio madi 300", "11927452722", LocalDate.parse("1993-01-13"));

    Paciente paciente = new Paciente("cachorro", "branco", "Liasa", "Ulli",
            "f", LocalDate.parse("2018-02-10"), proprietario);

    Medico medico = new Medico("Pedro", "Sousa", "23476487398", 123764,
            "veterinaria");

    Consulta consulta = new Consulta("Vomito", "intoxicacao", "soro",
            LocalDate.parse("2021-01-15") ,LocalTime.of(10, 20), paciente, medico);

     Consulta consulta2 = new Consulta("alergia", "intoxicacao", "soro",
             LocalDate.parse("2019-02-12") ,LocalTime.of(8, 20), paciente, medico);

     List<Consulta> consultas = new ArrayList<>();


    @Test
   public void agendarUmaConsulta() {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        ConsultaService mockServ = Mockito.mock(ConsultaService.class);
        Mockito.when(mock.cadastrarConsulta(Mockito.any(Consulta.class))).thenReturn(consulta);
        Mockito.when(mock.listar()).thenReturn(consultas);
        Mockito.when(mockServ.agendarConsulta(consulta)).thenReturn(consulta);
        Mockito.when(mockServ.agendarConsulta(consulta2)).thenReturn(consulta2);


        assertNotNull(consulta.getPaciente());

    }


    @Test
   public void alterarOsDadosDeUmaConsulta() {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        ConsultaService mockServ = Mockito.mock(ConsultaService.class);
        Mockito.when(mock.alterar(Mockito.any(Consulta.class))).thenReturn(consulta);
        Mockito.when(mockServ.alterar(consulta2)).thenReturn(consulta2);


        assertEquals("alergia", consulta2.getMotivo());
    }






}