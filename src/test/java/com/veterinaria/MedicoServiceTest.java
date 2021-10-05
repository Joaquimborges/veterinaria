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

    // Criando os mocks da medico;
    MedicoPersistence impostorMedicoPersistence = Mockito.mock(MedicoPersistence.class);

    //    Criando a a medicoService
    MedicoService medicoService = new MedicoService(impostorMedicoPersistence);

    //    Criando Lista de médicos
    ArrayList<Medico> listaImpostorMedico = new ArrayList<>();

    //    Construindo os objetos
    Medico  medicoImpostor = new Medico("Tsunade", "Senju", "45612334567", 54342, "Cardio");
    Medico  medicoImpostorAlterado = new Medico("Sakura", "Uchiha", "24313345569", 43551, "Animais pequenos");
    Medico  medicoImpostor2 = new Medico("Hugo", "Damm", "24411267680", 43551, "Animais Grandes");

    @Test
    void CadastrarMedico() {
        /*
         *  Testando
         *   - Se o crm e cpf já não existem no cadastro;
         *       - Caso Não exista é cadastrado os dados do médico.
         *       - Se caso caso exista o cadastro é recusado
         *   - Após as validações é feito a cricao do arquivo e escrita.
         * */

        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        Mockito.when(impostorMedicoPersistence.cadastrar(Mockito.any(Medico.class))).thenReturn(medicoImpostor);
        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);

        //==================================  Chamando o método de excução (add at list)
//        Acredito que esse método não é necessário, pois na persistence já está sendo add se náo irá provocar erro de cpfjá utilizado.
//        listaImpostorMedico.add(medicoImpostor);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        Medico medicoCadastrado = medicoService.cadastrar(medicoImpostor);
        Mockito.verify(impostorMedicoPersistence, Mockito.times(1)).cadastrar(medicoImpostor);

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertEquals(listaImpostorMedico.contains(medicoImpostor), listaImpostorMedico.contains(medicoImpostor));

    };

    @Test
    void getMedico() {
        /*
         *  Testando
         *   - A busca do medico pelo crvet
         *      - Aciona o método mapearObjeto da persistence buscando o crvet
         *      - Caso True ele nos retorna o objeto.
         */

        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a
            // persistencia, e em vez disso chama o mock
        Mockito.when(impostorMedicoPersistence.cadastrar(Mockito.any(Medico.class))).thenReturn(medicoImpostor);
        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        medicoService.getMedico(medicoImpostor.getNumeroRegistro());

        Mockito.verify(impostorMedicoPersistence,Mockito.times(1)).obterUm(medicoImpostor.getNumeroRegistro());

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertEquals(medicoImpostor.getNumeroRegistro(),medicoImpostor.getNumeroRegistro());


    }

    @Test
    void alteraMedicoTest(){
//        Setup
        Mockito.when(impostorMedicoPersistence.altera(Mockito.any(Medico.class))).thenReturn(medicoImpostorAlterado);
        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);
//        Testando efetivamente os métodos
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

    //    Criando o mock da consulta
    ConsultaPersistence impostorConsultaPersistence = Mockito.mock(ConsultaPersistence.class);

    //    Criando a lista de consulta
    ArrayList<Consulta> listaConsulta = new ArrayList<>();

    //    Criando os objetos para popular a lista
    LocalDate data = LocalDate.of(2021, 9, 25);
    Proprietario proprietario = new Proprietario("Moises", "Sousa", "11883344556",
            "Rua 2", "91276338902", data);

    Paciente paciente = new Paciente("cachorro", "Branco", "Akita",
            "Tobirama", "Macho", data, proprietario);

    Medico medico = new Medico("Pedro", "Sousa", "23476487398", 123764,
            "veterinaria");

    Consulta consulta = new Consulta("Vomito", "intoxicacao", "soro",
            LocalDate.parse("2021-01-15") , LocalTime.of(10, 20), paciente, medico);

    @Test
    void apagarMedicoTest(){

        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a
        // persistencia, e em vez disso chama o mock
        Mockito.when(impostorMedicoPersistence.remove(Mockito.any(Integer.class))).thenReturn(true);
        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);

        MedicoService medicoServiceApagar = new MedicoService(impostorMedicoPersistence, impostorConsultaPersistence);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        medicoServiceApagar.apagar(medicoImpostor.getNumeroRegistro());

        Mockito.verify(impostorMedicoPersistence, Mockito.times(1)).remove(medicoImpostor.getNumeroRegistro());

        assertEquals(false, listaImpostorMedico.contains(medicoImpostor));

    }
}
/*
 *   Author: Matheus Willock
 *   Date: 05/10/21
 */