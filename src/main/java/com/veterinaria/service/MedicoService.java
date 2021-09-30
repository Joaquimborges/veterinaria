package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Medico;
import com.veterinaria.persistence.ConsultaPersistence;
import com.veterinaria.persistence.MedicoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {


    private final MedicoPersistence persistence = new MedicoPersistence();
    private final ConsultaPersistence consultaPersistence = new ConsultaPersistence();



    private MedicoPersistence medicopersistence;
    private ConsultaPersistence consultaPersistence;

    public MedicoService(MedicoPersistence medicoPersistence){
        this.medicopersistence = medicoPersistence;
    }
    public MedicoService(ConsultaPersistence consultaPersistence){
        this.consultaPersistence = consultaPersistence;
    }
    @Autowired
    public MedicoService(MedicoPersistence medicoPersistence, ConsultaPersistence consultaPersistence){
        this.medicopersistence = medicoPersistence;
        this.consultaPersistence = consultaPersistence;
    }

    //valida cadastro médico por CRVET e CPF
    private boolean credenciaisNaoDuplicadas(Integer crvet, String cpf) {

        return medicopersistence.listarMedicos()
                .stream().noneMatch(x -> x.getNumeroRegistro()
                        .equals(crvet) || x.getCpf()
                        .equals(cpf));
    }

    private Boolean medicoNaoExisteNaConsulta(Integer crvet) {
        for (Consulta consulta : consultaPersistence.listar()) {
            if (consulta.getMedicoVeterinario().getNumeroRegistro().equals(crvet)) {
                return false;
            }
        }
        return true;
    }

    public Medico cadastrar(Medico medico) {
        if (credenciaisNaoDuplicadas(medico.getNumeroRegistro(), medico.getCpf())) {
            try {
                medicopersistence.cadastrar(medico);
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
        return medicopersistence.obterUm(crvet);
    }
  
    public List<Medico> listar(){
        return medicopersistence.listarMedicos();
    }

    public Medico alterar(Medico medico){
//        return persistence.altera(medico);
        if (medico != null){
            return persistence.altera(medico);
        }
        return null;



    }


    public Medico alterar(Medico medico){
        return medicopersistence.altera(medico);
    }


    public boolean apagar(Integer crvet){
        if (medicoNaoExisteNaConsulta(crvet)){
            return medicopersistence.remove(crvet);
        }
        return false;
    }
}
