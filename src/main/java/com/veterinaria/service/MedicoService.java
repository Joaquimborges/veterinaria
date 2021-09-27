package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Medico;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.MedicoPersistence;
import com.veterinaria.persistence.ProprietarioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService
{


    private final MedicoPersistence persistence = new MedicoPersistence();
    private final ConsultaPersistence consultaPersistence = new ConsultaPersistence();


    //valida cadastro médico por CRVET e CPF
    private boolean credenciaisNaoDuplicadas (Integer crvet, String cpf)
    {
        return persistence.listarMedicos()
                .stream().noneMatch(x -> x.getNumeroRegistro()
                        .equals(crvet) || x.getCpf()
                        .equals(cpf));
    }
  

    private Boolean medicoNaoExisteNaConsulta(Integer crvet) {
       for (Consulta consulta : consultaPersistence.listar()){
           if (consulta.getMedicoVeterinario().getNumeroRegistro().equals(crvet)){
               return false;
           }
       }
       return true;
    }
  

    public Medico cadastrar(Medico medico) {
        if (credenciaisNaoDuplicadas(medico.getNumeroRegistro(), medico.getCpf())) {
            try {
                persistence.cadastrar(medico);
                return medico;
            } catch (RuntimeException e) {
              e.printStackTrace();
            }
        } else {
            throw new RuntimeException("CPF ou CRM já em uso");
        }
        return null;
    }


    public Medico getMedico(Integer crvet){
        return persistence.obterUm(crvet);
    }

      
    public List<Medico> Listar(){
        return persistence.listarMedicos();
    }
      


    public Medico alterar(Medico medico){
        return persistence.altera(medico);
    }

      
    public Boolean apagar(Integer crvet){
        if (medicoNaoExisteNaConsulta(crvet)){
            return persistence.remove(crvet);
        }
        return false;
    }



}
