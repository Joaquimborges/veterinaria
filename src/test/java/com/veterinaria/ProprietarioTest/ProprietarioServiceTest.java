package com.veterinaria.ProprietarioTest;

import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.PacientePersistence;
import com.veterinaria.persistence.ProprietarioPersistence;
import com.veterinaria.service.PacienteService;
import com.veterinaria.service.ProprietarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class ProprietarioServiceTest
{
    Proprietario naoEsta = new Proprietario("Samira", "Sumira", "333.333.333-33", "Rua Hua, 23", "(11)7650454", LocalDate.of(2000, 12, 3));
    Proprietario esta = new Proprietario("Marcela", "Martelo Marmelo", "222.222.222-22", "Rua Morondinumoraninguém, s/n - Casa do Agepê, SP - Brasil", "11-5551011", LocalDate.of(1993, 04, 10));


    ProprietarioPersistence mockPropPers = Mockito.mock(ProprietarioPersistence.class);
    ProprietarioService mockPropServ = new ProprietarioService();


    @Test
    public void proprietarioNaoExisteNaListaDeProprietarios()
    {
        Mockito.when(mockPropPers.obterUm(anyString())).thenReturn(naoEsta);

        Proprietario p = mockPropServ.getProprietario("333.333.333-33");

        assertNull(p);
    }

    @Test
    public void proprietarioExisteNaListaDeProprietarios()
    {
        Mockito.when(mockPropPers.obterUm(anyString())).thenReturn(esta);

        Proprietario p = mockPropServ.getProprietario("222.222.222-22");

        assertNotNull(p);
    }


}
