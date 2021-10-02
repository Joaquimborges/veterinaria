package com.veterinaria;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Medico;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.ProprietarioPersistence;
import com.veterinaria.service.ProprietarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

class ProprietarioServiceTest {

    /**
     * Esta classe ProprietarioServiceTest, foi criada em grupo como modelo após a primeira aula de teste.
     * Para fixação de conceito e aplicação do apredizado.
     *
     * Posteriormente fiz alterações pontuais sozinho e aqui e em outros pontos do projeto com injeção de dependencias,
     * e inclusão do Mockito.verify(), para verificar se os, métodos estão sendo chamados corretamente e quantas vezes
     * estão sendo chamados.
     *
     * Autor
     * Alex
     */
    ProprietarioPersistence mockProprietarioPersistence = Mockito.mock(ProprietarioPersistence.class);
    ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);

    ArrayList<Proprietario> lista = new ArrayList<>();
    ArrayList<Consulta> listaConsulta = new ArrayList<>();

    LocalDate data = LocalDate.of(2021, 9, 25);
    Proprietario proprietario = new Proprietario("Moises", "Sousa", "11883344556",
            "Rua 2", "91276338902", data);
    Proprietario proprietario2 = new Proprietario("Pedro", "Sousa", "11773344556",
            "Rua 2", "91276338902", data);
    Proprietario proprietario3 = new Proprietario("Henrique", "Sousa", "11883349556",
            "Rua 2", "91276338902", data);

    Paciente paciente = new Paciente("cachorro", "Branco", "Akita",
            "Tobirama", "Macho", data, proprietario);
    Paciente paciente2 = new Paciente("cachorro", "Branco", "Akita",
            "Tobirama", "Macho", data, proprietario2);

    Medico medico = new Medico("Pedro", "Sousa", "23476487398", 123764,
            "veterinaria");

    Consulta consulta = new Consulta("Vomito", "intoxicacao", "soro",
            LocalDate.parse("2021-01-15") , LocalTime.of(10, 20), paciente, medico);

    @Test
    void cadastrarProprietarioTest() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        Mockito.when(mockProprietarioPersistence.cadastrar(Mockito.any(Proprietario.class))).thenReturn(proprietario2);
        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mockProprietarioPersistence);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        Proprietario p = proprietarioService.cadastrar(proprietario2);
        Mockito.verify(mockProprietarioPersistence, Mockito.times(1)).cadastrar(proprietario2);
        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotNull(p);
    }

    @Test
    void cpfProprietarioEmUsoTest() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock

        Mockito.when(mockProprietarioPersistence.cadastrar(Mockito.any(Proprietario.class))).thenReturn(proprietario2);

        lista.add(proprietario2);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mockProprietarioPersistence);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.cadastrar(proprietario2);

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertEquals("11773344556", proprietario2.getCpf());
    }

    @Test
    void getProprietarioTest() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        lista.add(proprietario);
        Mockito.when(mockProprietarioPersistence.obterUm(Mockito.any(String.class))).thenReturn(proprietario);
        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mockProprietarioPersistence);
        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        Proprietario p = proprietarioService.getProprietario(this.proprietario.getCpf());

        Mockito.verify(mockProprietarioPersistence,Mockito.times(1)).obterUm(proprietario.getCpf());

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertEquals(p.getCpf(),proprietario.getCpf());
    }

    @Test
    void alteraProprietario() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        lista.add(proprietario);

        Mockito.when(mockProprietarioPersistence.altera(Mockito.any(Proprietario.class))).thenReturn(proprietario);
        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mockProprietarioPersistence);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        Proprietario p = proprietarioService.altera(proprietario);
        //================================== Verifica através do assert, o que definirmos que queremos testar
        Mockito.verify(mockProprietarioPersistence, Mockito.times(1)).altera(proprietario);

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertEquals(proprietario.getNome(), p.getNome());
    }

    @Test
    void listaProprietario() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        Mockito.when(mockProprietarioPersistence.remove(Mockito.any(String.class))).thenReturn(true);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mockProprietarioPersistence);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        List<Proprietario> p = proprietarioService.listar();

        Mockito.verify(mockProprietarioPersistence, Mockito.times(1)).listarProprietarios();

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertEquals(p.size(), this.lista.size());
    }

    @Test
    void apagarProprietario() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        Mockito.when(mockProprietarioPersistence.remove(Mockito.any(String.class))).thenReturn(true);
        lista.add(proprietario);
        listaConsulta.add(consulta);
        Mockito.when(mockConsultaPersistence.listar()).thenReturn(listaConsulta);
        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mockProprietarioPersistence);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.apagar(proprietario.getCpf());

        Mockito.verify(mockProprietarioPersistence, Mockito.times(1)).remove(proprietario.getCpf());
        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotEquals(true,lista.contains(proprietario));
    }


}
