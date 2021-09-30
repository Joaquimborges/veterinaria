package com.veterinaria;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Medico;
import com.veterinaria.entity.Paciente;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.MedicoPersistence;
import com.veterinaria.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MedicoServiceTest {

    MedicoPersistence mockMedicoPersistence = Mockito.mock(MedicoPersistence.class);
    ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);

    ArrayList<Medico> lista           = new ArrayList<>();
    ArrayList<Consulta> listaConsulta   = new ArrayList<>();

    Medico medico1 = new Medico("DrPaulo", "Roberto", "12345678", 123, "veterinario");
    Medico medico2 = new Medico("DrRoberto", "Mineiro", "98765432", 456, "animal");
    Medico medico3 = new Medico("DrMarcelo", "Teste", "12312312", 756, "animal2");
    Medico medico4 = new Medico("DrHugo", "Damm", "098765", 890, "animal2");

    @Test
    void cadastrarMedicoTest(){
        Mockito.when(mockMedicoPersistence.cadastrar(Mockito.any(Medico.class))).thenReturn(medico3);
        lista.add(medico3);
        Mockito.when(mockMedicoPersistence.listarMedicos()).thenReturn(lista);

        MedicoService medicoService = new MedicoService();

        medicoService.cadastrar(medico3);
        assertNotNull(medico3);
    }

    @Test
    void alteraMedicoTest(){
        lista.add(medico1);

        Mockito.when(mockMedicoPersistence.altera(Mockito.any(Medico.class))).thenReturn(medico2);
        Mockito.when(mockMedicoPersistence.listarMedicos()).thenReturn(lista);

        MedicoService medicoService = new MedicoService();

        medicoService.alterar(medico2);

        String expectedNome = "DrRoberto";
        String actualNome = medico2.getNome();

        assertTrue(actualNome.equals(expectedNome));

    }

    @Test
    void listaMedicoTest(){
        Mockito.when(mockMedicoPersistence.remove(Mockito.any(Integer.class))).thenReturn(true);
        Mockito.when(mockMedicoPersistence.listarMedicos()).thenReturn(lista);

        MedicoService medicoService = new MedicoService();

        medicoService.listar();

        assertNotNull(medico1);
    }

    @Test
    void apagarMedicoTest(){
        Mockito.when(mockMedicoPersistence.remove(Mockito.any(Integer.class))).thenReturn(true);
        Mockito.when(mockConsultaPersistence.listar()).thenReturn(listaConsulta);
        Mockito.when(mockMedicoPersistence.listarMedicos()).thenReturn(lista);

        MedicoService medicoService = new MedicoService();

        medicoService.apagar(medico1.getNumeroRegistro());

        assertNotEquals(true, lista.contains(medico1));

    }
}
