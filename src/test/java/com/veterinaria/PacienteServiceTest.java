package com.veterinaria;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.PacientePersistence;
import com.veterinaria.persistence.ProprietarioPersistence;
import com.veterinaria.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PacienteServiceTest {

    PacientePersistence mockPacientePersistence = Mockito.mock(PacientePersistence.class);
    ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);
    ProprietarioPersistence mockProprietarioPersistence = Mockito.mock(ProprietarioPersistence.class);

    ArrayList<Paciente> listaPaciente = new ArrayList<>();
    ArrayList<Proprietario> listaProprietario = new ArrayList<>();
    ArrayList<Consulta> listaConsulta = new ArrayList<>();

    LocalDate data = LocalDate.of(2021, 9, 25);

    Proprietario proprietario = new Proprietario("João", "Paulo", "11883344556", "Rua 2", "91276338902", data);
    Proprietario proprietario2 = new Proprietario("Mauro", "Rocha", "11773344556", "Rua 2", "91276338902", data);

    Paciente paciente1 = new Paciente("cachorro", "branca", "bulldog", "Amora", "femea", data, proprietario);
    //Paciente paciente1update = new Paciente("cachorro", "branca-e-preto", "bulldog", "Amora", "femea", data, proprietario);

    Paciente paciente2 = new Paciente("cachorro", "preto", "bulldog", "Apollo", "macho", data, proprietario2);

    /**
     * Autor: Marcelo de Oliveira Santos
     * Data: 04.10.2021
     */

    @Test
    void cadastrarPacienteTest(){
        Mockito.when(mockPacientePersistence.cadastrar(Mockito.any(Paciente.class))).thenReturn(paciente1);
        listaProprietario.add(proprietario);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(listaProprietario);

        PacienteService pacienteService = new PacienteService(mockPacientePersistence, mockProprietarioPersistence, mockConsultaPersistence);

        Paciente p = pacienteService.cadastraPaciente(paciente1);

        assertNotNull(p);
        assertTrue(p.getNome().equals(paciente1.getNome()));

    }

    /**
     * Autor: Marcelo de Oliveira Santos
     * Data: 04.10.2021
     */
    @Test
    void alteraPaciente(){
        listaPaciente.add(paciente1);

        Mockito.when(mockPacientePersistence.altera(Mockito.any(Paciente.class))).thenReturn(paciente1);
        Mockito.when(mockPacientePersistence.listarPacientes()).thenReturn(listaPaciente);

        PacienteService pacienteService = new PacienteService(mockPacientePersistence);

        pacienteService.altera(paciente1);

        String expectedNome = "Amarildo";
        String actualNome   = paciente1.getNome();

        assertEquals(expectedNome, actualNome);
    }



}
