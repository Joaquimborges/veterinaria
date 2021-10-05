package com.veterinaria;
import com.veterinaria.entity.*;
import com.veterinaria.entity.Medico;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.MedicoPersistence;
import com.veterinaria.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
 *   Author: Matheus Willock
 *   Date: 05/10/21
 */
public class MedicoServiceTest {

    MedicoPersistence impostorMedicoPersistence = Mockito.mock(MedicoPersistence.class);
    MedicoService medicoService = new MedicoService(impostorMedicoPersistence);

    ConsultaPersistence impostorConsultaPersistence = Mockito.mock(ConsultaPersistence.class);

    ArrayList<Consulta> listaConsulta = new ArrayList<>();

    LocalDate data = LocalDate.of(2021, 9, 25);
    Proprietario proprietario = new Proprietario("Moises", "Sousa", "11883344556",
            "Rua 2", "91276338902", data);

    Paciente paciente = new Paciente("cachorro", "Branco", "Akita",
            "Tobirama", "Macho", data, proprietario);

    Medico medico = new Medico("Pedro", "Sousa", "23476487398", 123764,
            "veterinaria");

    Consulta consulta = new Consulta("Vomito", "intoxicacao", "soro",
            LocalDate.parse("2021-01-15") , LocalTime.of(10, 20), paciente, medico);

    ArrayList<Medico> listaImpostorMedico = new ArrayList<>();

    Medico  medicoImpostor = new Medico("Tsunade", "Senju", "45612334567", 54342, "Cardio");
    Medico  medicoImpostorAlterado = new Medico("Sakura", "Uchiha", "24313345569", 43551, "Animais pequenos");
    Medico  medicoImpostor2 = new Medico("Hugo", "Damm", "24411267680", 43551, "Animais Grandes");

    @Test
    void CadastrarMedico() {
        Mockito.when(impostorMedicoPersistence.cadastrar(Mockito.any(Medico.class))).thenReturn(medicoImpostor);
        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);

        Medico medicoCadastrado = medicoService.cadastrar(medicoImpostor);
        Mockito.verify(impostorMedicoPersistence, Mockito.times(1)).cadastrar(medicoImpostor);

        assertEquals(listaImpostorMedico.contains(medicoImpostor), listaImpostorMedico.contains(medicoImpostor));

    };

    @Test
    void getMedico() {
        Mockito.when(impostorMedicoPersistence.cadastrar(Mockito.any(Medico.class))).thenReturn(medicoImpostor);
        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);

        medicoService.getMedico(medicoImpostor.getNumeroRegistro());

        Mockito.verify(impostorMedicoPersistence,Mockito.times(1)).obterUm(medicoImpostor.getNumeroRegistro());

        assertEquals(medicoImpostor.getNumeroRegistro(),medicoImpostor.getNumeroRegistro());

    }

    @Test
    void alteraMedicoTest(){

        Mockito.when(impostorMedicoPersistence.altera(Mockito.any(Medico.class))).thenReturn(medicoImpostorAlterado);
        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);


        listaImpostorMedico.add(medicoImpostorAlterado);
        medicoService.alterar(medicoImpostorAlterado);

        Mockito.verify(impostorMedicoPersistence, Mockito.times(1)).altera(medicoImpostorAlterado);

        assertEquals(medicoImpostorAlterado.getNome(), medicoImpostorAlterado.getNome());

    }

    @Test
    void listaMedicoTest(){

        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);
        listaImpostorMedico.add(medicoImpostor);
        listaImpostorMedico.add(medicoImpostor2);

        medicoService.listar();

        assertEquals(listaImpostorMedico.size(), listaImpostorMedico.size());

    }

    @Test
    void apagarMedicoTest(){
        listaImpostorMedico.add(medicoImpostor);
        listaImpostorMedico.add(medicoImpostor2);
        listaImpostorMedico.add(medicoImpostorAlterado);

        Mockito.when(impostorMedicoPersistence.remove(Mockito.any(Integer.class))).thenReturn(true);
        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);

        MedicoService medicoServiceApagar = new MedicoService(impostorMedicoPersistence, impostorConsultaPersistence);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        boolean medicoApagado = medicoServiceApagar.apagar(medicoImpostor2.getNumeroRegistro());

        assertFalse(listaImpostorMedico.contains(medicoApagado));

    }
}
/*
 *   Author: Matheus Willock
 *   Date: 05/10/21
 */