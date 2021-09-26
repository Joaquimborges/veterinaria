package com.veterinaria.service;

import com.veterinaria.entity.Medico;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.MedicoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService
{


    @Autowired
    MedicoPersistence medicoPersistence;
    ConsultaPersistence consultaPersistence;



    private boolean credenciaisNaoDuplicadas (Integer crvet, String cpf)
    {
        return medicoPersistence.listarMedicos()
                .stream()
                .filter(x-> x.getNumeroRegistro()
                        .equals(crvet) || x.getCpf()
                            .equals(cpf)).count() == 0;
    }

    public Medico cadastrar(Medico medico)
    {
        Integer crvet = medico.getNumeroRegistro();
        String cpf = medico.getCpf();

       if(credenciaisNaoDuplicadas(crvet, cpf)) {
           try {
               medicoPersistence.cadastrar(medico);
               return medico;
           } catch (RuntimeException e) {
               e.printStackTrace();
           }

       }



       else {
           throw new RuntimeException("Erro: CPF ou CRVET já em uso!");
       }
        return null;

    }

    public Medico altera(Medico medico){
        if (medico != null){
            return medicoPersistence.altera(medico);
        }
        return null;
    }

    public Medico getMedico(Integer crvet){
        return medicoPersistence.obterUm(crvet);
    }

    public List<Medico> Listar(){
        return medicoPersistence.listarMedicos();
    }

    public boolean apagar(String crvet){
        if (medicoNaoExisteNaConsulta(Integer.valueOf(crvet))){
            return medicoPersistence.remove(Integer.valueOf(crvet));
        }
        return false;
    }

    private boolean medicoNaoExisteNaConsulta(Integer crvet) {

        return consultaPersistence.listar()
                .stream()
                    .filter(x-> x.getMedicoVeterinario()
                            .getNumeroRegistro().equals(crvet)).count() == 0;

    }

}
