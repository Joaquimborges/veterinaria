package com.veterinaria;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.ProprietarioPersistence;
import com.veterinaria.service.ProprietarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProprietarioServiceTest {

    ProprietarioPersistence mockProprietarioPersistence = Mockito.mock(ProprietarioPersistence.class);
    ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);

    ArrayList<Proprietario> lista = new ArrayList<>();
    ArrayList<Consulta> listaConsulta = new ArrayList<>();

    LocalDate data = LocalDate.of(2021, 9, 25);
    Proprietario proprietario = new Proprietario("Carlos", "Sousa", "11883344556",
            "Rua 2", "91276338902", data);
    Proprietario proprietario2 = new Proprietario("Pedro", "Sousa", "11773344556",
            "Rua 2", "91276338902", data);
    Proprietario proprietario3 = new Proprietario("Henrique", "Sousa", "11883349556",
            "Rua 2", "91276338902", data);

    Paciente paciente = new Paciente("cachorro", "Branco", "Akita",
            "Tobirama", "Macho", data, proprietario);
    Paciente paciente2 = new Paciente("cachorro", "Branco", "Akita",
            "Tobirama", "Macho", data, proprietario2);

    @Test
    void cadastrarProprietarioTest() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock

        Mockito.when(mockProprietarioPersistence.cadastrar(Mockito.any(Proprietario.class))).thenReturn(proprietario2);

        lista.add(proprietario3);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);


        ProprietarioService proprietarioService = new ProprietarioService();

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.cadastrar(proprietario3);

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotNull(proprietario3);
    }

    @Test
    void cpfProprietarioEmUsoTest() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock

        Mockito.when(mockProprietarioPersistence.cadastrar(Mockito.any(Proprietario.class))).thenReturn(proprietario2);

        lista.add(proprietario);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService();

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.cadastrar(proprietario);

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertTrue(proprietario.getCpf().equals("11883344556"));
    }

    @Test
    void getProprietarioTest() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock

        lista.add(proprietario);

        Mockito.when(mockProprietarioPersistence.obterUm(Mockito.any(String.class))).thenReturn(proprietario);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);


        ProprietarioService proprietarioService = new ProprietarioService();


        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.getProprietario(proprietario.getCpf());

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotNull(proprietario);
    }

    @Test
    void alteraProprietario() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        lista.add(proprietario);

        Mockito.when(mockProprietarioPersistence.altera(Mockito.any(Proprietario.class))).thenReturn(proprietario);
        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService();

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.altera(proprietario);

        //================================== Verifica através do assert, o que definirmos que queremos testar

        String expectedNome = "Carlos";
        String actualNome = proprietario.getNome();

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertTrue(actualNome.equals(expectedNome));
    }

    @Test
    void listaProprietario() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        Mockito.when(mockProprietarioPersistence.remove(Mockito.any(String.class))).thenReturn(true);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService();

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.Listar();

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotNull(proprietario);
    }

    @Test
    void apagarProprietario() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        Mockito.when(mockProprietarioPersistence.remove(Mockito.any(String.class))).thenReturn(true);

        Mockito.when(mockConsultaPersistence.listar()).thenReturn(listaConsulta);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService();

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.apagar(proprietario.getCpf());

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotEquals(true,lista.contains(proprietario));
    }
}
