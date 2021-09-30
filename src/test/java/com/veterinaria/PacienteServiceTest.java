package com.veterinaria;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.PacientePersistence;
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

    ArrayList<Paciente> lista = new ArrayList<>();
    ArrayList<Consulta> listaConsulta = new ArrayList<>();

    LocalDate data = LocalDate.of(2021, 9, 25);

    Proprietario proprietario = new Proprietario("João", "Paulo", "11883344556", "Rua 2", "91276338902", data);
    Proprietario proprietario2 = new Proprietario("Mauro", "Rocha", "11773344556", "Rua 2", "91276338902", data);

    Paciente paciente1 = new Paciente("cachorro", "branca", "bulldog", "Amora", "femea", data, proprietario);
    Paciente paciente2 = new Paciente("cachorro", "preto", "bulldog", "Apollo", "macho", data, proprietario2);

    @Test
    void cadastrarPacienteTest(){
        Mockito.when(mockPacientePersistence.cadastrar(Mockito.any(Paciente.class))).thenReturn(paciente1);
        lista.add(paciente1);
        Mockito.when(mockPacientePersistence.listarPacientes()).thenReturn(lista);

        PacienteService pacienteService = new PacienteService();

        pacienteService.cadastraPaciente(paciente1);

//        assertNotNull(paciente1);
        assertTrue(lista.contains(paciente1));
    }

//    @Test
//    void pacienteEmUsoTest(){
//        Mockito.when(mockPacientePersistence.cadastrar(Mockito.any(Paciente.class))).thenReturn(paciente1);
//        lista.add(paciente1);
//        Mockito.when(mockPacientePersistence.listarPacientes()).thenReturn(lista);
//        PacienteService pacienteService = new PacienteService();
//
//        pacienteService.cadastraPaciente(paciente1);
//
//        assertTrue(paciente1.getNome().equals("Mauro"));
//    }


    @Test
    void alteraPaciente(){
        lista.add(paciente1);

        Mockito.when(mockPacientePersistence.altera(Mockito.any(Paciente.class))).thenReturn(paciente1);
        Mockito.when(mockPacientePersistence.listarPacientes()).thenReturn(lista);

        PacienteService pacienteService = new PacienteService();

        pacienteService.altera(paciente1);

        String expectedNome = "Rafael";
        String actualNome   = paciente1.getNome();
    }

    @Test
    void listaPaciente(){
        Mockito.when(mockPacientePersistence.remove(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
        Mockito.when(mockPacientePersistence.listarPacientes()).thenReturn(lista);

        PacienteService pacienteService = new PacienteService();

        pacienteService.listarPacientes();

        assertNotNull(paciente1);
    }


    @Test
    void apagarPaciente(){

        Mockito.when(mockPacientePersistence.remove(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
        Mockito.when(mockConsultaPersistence.listar()).thenReturn(listaConsulta);
        Mockito.when(mockPacientePersistence.listarPacientes()).thenReturn(lista);

        PacienteService pacienteService = new PacienteService();

        pacienteService.apagar(paciente1.getNome(), proprietario.getCpf());

        assertNotEquals(true, lista.contains(paciente1));

    }

}
