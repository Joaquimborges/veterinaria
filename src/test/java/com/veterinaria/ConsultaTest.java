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

     /**
      * Autor Joaquim Borges
      */
    @Test
   public void agendarUmaConsulta() {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        Mockito.when(mock.cadastrarConsulta(Mockito.any(Consulta.class))).thenReturn(consulta);
        Mockito.when(mock.listar()).thenReturn(consultas);

        ConsultaService consultaService = new ConsultaService(mock);
        consultaService.agendarConsulta(consulta);
        consultaService.agendarConsulta(consulta2);



        assertNotNull(consulta.getPaciente());

    }


     /**
      * Autor Joaquim Borges
      */
    @Test
   public void alterarOsDadosDeUmaConsulta() {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        Mockito.when(mock.alterar(Mockito.any(Consulta.class))).thenReturn(consulta);

        ConsultaService consultaService = new ConsultaService(mock);
        consultaService.alterar(consulta2);



        assertEquals("alergia", consulta2.getMotivo());
    }

     /**
      * Autor Joaquim Borges
      */
    @Test
   public void listarConsultasOrdenadaPelaDataDecrescente() {
        consultas.add(consulta2);
        consultas.add(consulta);
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        Mockito.when(mock.listar()).thenReturn(consultas);

        ConsultaService consultaService = new ConsultaService(mock);
        List<Consulta> consultaOrdenada = consultaService
                .listarConsultaPorData(paciente.getNome(), proprietario.getCpf());

        assertEquals("Vomito", consultaOrdenada.get(0).getMotivo());
    }






}
