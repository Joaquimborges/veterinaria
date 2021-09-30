package com.veterinaria;
import com.veterinaria.entity.Medico;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.MedicoPersistence;
import com.veterinaria.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
*   Author: Matheus Willock
*   Date: 29/09/21
* */
public class MedicoServiceTest {

// Criando os mocks da medico e Consulta Persistence;
    MedicoPersistence impostorMedicoPersistence = Mockito.mock(MedicoPersistence.class);
    ConsultaPersistence impostorLitaConsulta = Mockito.mock(ConsultaPersistence.class);

//    Criando a a medicoService
    MedicoService medicoService = new MedicoService();
//    Criando Lista medico
    ArrayList<Medico> listaImpostorMedico = new ArrayList<>();

    Medico  medicoImpostor = new Medico("Tsunade", "Senju", "45612334567", 54342, "Cardio");
    Medico  medicoImpostor1 = new Medico("Sakura", "Uticha", "24313345569", 10102, "Animais pequenos");

/*
*  Testando
*   - Se o crm e cpf já não existem no cadastro;
*       - Caso Não exista é cadastrado os dados do médico.
*       - Se caso caso exista o cadastro é recusado
*   - Após as validações é feito a cricao do arquivo e escrita.
* */
    @Test
    void CadastrorMedico() {

        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock

        Mockito.when(impostorMedicoPersistence.cadastrar(medicoImpostor1)).thenReturn(medicoImpostor1);

        listaImpostorMedico.add(medicoImpostor1);

//        Mockito.when(impostorMedicoPersistence.listarMedicos()).thenReturn(listaImpostorMedico);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas

        medicoService.cadastrar(medicoImpostor1);

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotNull(medicoImpostor1);
        
    };

/*
 *  Testando
 *   - A busca do medico pelo crvet
 *      - Aciona o método mapearObjeto da persistence buscando o crvet
 *      - Cas True ele nos retorna o objeto.
 */
    @Test
    void getMedico() {
        //==================================  Preparo do setup, ou seja prepara os caminhos que deveriam chamar a persistencia, e em vez disso chama o mock

        Mockito.when(impostorMedicoPersistence.obterUm(medicoImpostor1.getNumeroRegistro())).thenReturn(medicoImpostor1);

        //=================================== Testa efetivamente nosso código, as regras que foram criadas
        medicoService.getMedico(medicoImpostor1.getNumeroRegistro());

        //================================== Verifica através do assert, o que definirmos que queremos testar
        assertNotNull(medicoImpostor1);
    }
}
/*
 *   Author: Matheus Willock
 *   Date: 29/09/21
 * */