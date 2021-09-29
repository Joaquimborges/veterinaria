package com.veterinaria;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.PacientePersistence;
import com.veterinaria.persistence.ProprietarioPersistence;
import com.veterinaria.service.ProprietarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VendedorServiceTest {


    @Test
    void cadastrarProprietarioTest() throws IOException {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        ProprietarioPersistence mockProprietarioPersistence = Mockito.mock(ProprietarioPersistence.class);
        ArrayList<Proprietario> lista = new ArrayList<>();

        LocalDate data = LocalDate.of(2021, 9, 25);
        Proprietario proprietario = new Proprietario("Augusto", "Sousa", "11223344556",
                "Rua 2", "91276338902", data);

        Mockito.when(mockProprietarioPersistence.cadastrar(Mockito.any(Proprietario.class))).thenReturn(proprietario);

        lista.add(proprietario);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);


        ProprietarioService proprietarioService = new ProprietarioService();

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.cadastrar(proprietario);

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotNull(proprietario);
//        assertNotNull(proprietario.getCpf());
    }


    @Test
    void proprietarioCpfNull() throws IOException {

        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
        ProprietarioPersistence mockProprietarioPersistence = Mockito.mock(ProprietarioPersistence.class);
        ArrayList<Proprietario> lista = new ArrayList<>();

        LocalDate data = LocalDate.of(2021, 9, 25);
        Proprietario proprietario = new Proprietario("Augusto", "Sousa", null,
                "Rua 2", "91276338902", data);

        Mockito.when(mockProprietarioPersistence.cadastrar(Mockito.any(Proprietario.class))).thenReturn(proprietario);

//        lista.add(proprietario);

        Mockito.when(mockProprietarioPersistence.listarProprietarios()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService();

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        proprietarioService.cadastrar(proprietario);

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNull(proprietario.getCpf());
    }
//    @Test
//    void naoDeve_Cadastrar_Vendedor_Quando_Cpf_Existente() throws IOException {
//        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock
//        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
//
//        ArrayList<Proprietario> lista = new ArrayList<>();
//
//        LocalDate data = LocalDate.of(2021, 9, 25);
//        Proprietario proprietario = new Proprietario("Augusto", "Sousa", "11223344556",
//                "Rua 2", "91276338902", data);
//
//        lista.add(proprietario);
//
//        Mockito.when(mock.altera(Mockito.any(Proprietario.class)).thenReturn(vendedor);
//
//        Mockito.when(mock.listagem())
//                .thenReturn(lista);
//
//
//        VendedorService vendedorService = new VendedorService(mock);
//
//
//        //=================================== Testa efetivamente nosso código, as regras que foram criadas
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
//            vendedorService.cadastrar(vendedor);
//        });
//        //================================== Verifica através do assert, o que definirmos que queremos testar
//        String expectedMessage = "CPF em uso";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void naoDeve_Cadastrar_Vendedor_Menor_Idade() throws IOException {
//        VendedorPersistence mock = Mockito.mock(VendedorPersistence.class);
//
//        ArrayList<Vendedor> lista = new ArrayList<>();
//        Vendedor vendedor = new Vendedor("922.345.123-10", "Kenyo", "Gyn", "GO", LocalDate.parse("2005-02-25")); 				//preparacao do cenário (setup)
//
//        Mockito.when(mock.cadastro(Mockito.any(Vendedor.class), Mockito.any(Boolean.class)))
//                .thenReturn(vendedor);
//        Mockito.when(mock.listagem())
//                .thenReturn(lista);
//
//        VendedorService vendedorService = new VendedorService(mock);
//
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
//            vendedorService.cadastrar(vendedor);
//        });
//
//        String expectedMessage = "Vendedor menor de idade";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }

}