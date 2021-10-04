package com.veterinaria.PacienteServiceTest;

import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.PacientePersistence;
import com.veterinaria.persistence.ProprietarioPersistence;
import com.veterinaria.service.PacienteService;
import com.veterinaria.service.ProprietarioService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PacienteServiceTest {


    PacienteService mockPaciServ = new PacienteService();

    ProprietarioService mockPropServ = new ProprietarioService();

    ArrayList<Paciente> listaConsulta = new ArrayList<>();
    ArrayList<Proprietario> lista = new ArrayList<>();


    PacientePersistence mockPaciPers = mock(PacientePersistence.class);
    ProprietarioPersistence mockPropPers = mock(ProprietarioPersistence.class);




    Proprietario marcelo = new Proprietario("Marcela", "Martelo Marmelo", "222.222.222-22", "Rua Morondinumoraninguém, s/n - Casa do Agepê, SP - Brasil", "11-5551011", LocalDate.of(1993, 04, 10));
    Paciente bruno = new Paciente("Canis Familiaris", "Preta", "Shih-tzu", "Bruno", "M", LocalDate.of(2020, 05, 11), marcelo);

    //alteraremos o cadastro do paciente bruno com o objeto bruno2
     Paciente bruno2 = new Paciente("Canis Familiaris", "Preto-Branco-amarelo", "Shih-tzu", "Bruno", "M", LocalDate.of(2020, 05, 11), marcelo);


     List<Paciente> listaPaciente = new ArrayList<>();



    @Test
    public void testaCadastraInserido () {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock


        Mockito.when(mockPropPers.cadastrar(any(Proprietario.class))).thenReturn(marcelo);
        Mockito.when(mockPaciPers.cadastrar(any(Paciente.class))).thenReturn(bruno);

        List<Paciente> listaPaciente = new ArrayList<>();
        Mockito.when(mockPaciPers.listarPacientes()).thenReturn(listaPaciente);



//=================================== Testa efetivamente nosso código, as regras que foram criadas

       //mockPropServ.cadastrar(marcelo);
        Paciente b = mockPaciServ.cadastraPaciente(bruno);

       assertEquals(b, bruno);

    }

    @Test
    public void obterUmExistente() {

        Paciente esta;
        try {
            Mockito.when(mockPaciServ.obterPaciente(anyString(), anyString())).thenReturn(bruno);

            esta = mockPaciServ.obterPaciente(bruno.getNome(), bruno.getProprietario().getCpf());

            assertEquals(esta, bruno);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }


    }

    @Test
    public void obterUmInexistente() {

        Paciente esta = null;
        try {
            Mockito.when(mockPaciServ.obterPaciente(anyString(), anyString())).thenReturn(bruno);

            esta = mockPaciServ.obterPaciente("GrimHilde", "cpf falso");


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        finally {
            assertNotEquals(esta, bruno);
        }


    }

    @Test
    public void altera()
    {

        try {
            Mockito.when(mockPaciServ.altera(any())).thenReturn(bruno2);
        } catch (NullPointerException e)
        {
            System.out.println(e.getLocalizedMessage());
        }

        finally {
            Paciente p = mockPaciServ.altera(bruno2);
            assertNotNull(p);
            assertNotEquals(p, bruno);
        }
    }


}


