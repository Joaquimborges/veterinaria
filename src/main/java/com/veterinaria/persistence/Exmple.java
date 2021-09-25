package com.veterinaria.persistence;

import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.service.PacienteService;

import java.io.IOException;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Exmple
{
    
    @GetMapping("/hello")

    public String hello ()
    {
        return "Hello";
    }
    

        public static void main(String[] args) throws IOException
        {

//            PacientePersistence ps = new PacientePersistence();
             PacienteService ps = new PacienteService();

            LocalDate date = LocalDate.of(1993, 9, 15);
            Proprietario proprietario
                    = new Proprietario
                        (
                    "Pedro Hugo", "Paulo", "23782341805", "rua 2",
                    "1190884625", date
                        );

            Proprietario proprietario2 = new Proprietario(
                    "Hugo", "Damm", "11223344556", "rua 1",
                    "1190884625", date
            );

            Paciente fofinha = new Paciente(
                    "Cannis Familiaris", "Bege", "Fox Terrrier", "Fofinha",
                    "femea", date, proprietario2
            );

            Paciente maximus = new Paciente(
                    "Cannis Familiaris", "preto", "BullDog", "Maximus",
                    "macho", date, proprietario
            );


          System.out.println(ps.cadastraPaciente(fofinha));
//          System.out.println(ps.cadastrarPaciente(maximus));
            //  ps.cadastrar(proprietario2);

//        System.out.println(ps.listarPacientes());
//          System.out.println(ps.obterPaciente("Maximus","macho","BullDog","preto"));
//          System.out.println(ps.obterPaciente("Maximus", proprietario.getCpf()));

//        System.out.println(ps.editarPaciente(maximus));

//            System.out.println(ps.removerPaciente("Maximus","macho","BullDog","preto"));
//        ps.remove("11223344556");

        }
}

