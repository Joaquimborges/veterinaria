package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.persistence.ConsultaPersistence;
import org.springframework.stereotype.Service;


import java.time.LocalDate;


import java.util.*;


@Service
public class ConsultaService {

    private final ConsultaPersistence consultaPersistence;
    private  boolean existeConsultaParaOrganizar = false;



    public ConsultaService(ConsultaPersistence consultaPersistence){
        this.consultaPersistence = consultaPersistence;
    }

    public Consulta agendarConsulta(Consulta consulta) {
        consultaPersistence.cadastrarConsulta(consulta);
        return consulta;

    }

    public Consulta alterar(Consulta consulta){
        if (consulta != null){
            return consultaPersistence.alterar(consulta);
        }
        return null;
    }


    public List<Consulta> listarConsultaPorData(String nomePaciente, String cpfProprietario){
        List<Consulta> consultasDoPaciente = new ArrayList<>();
        for (Consulta consulta : consultaPersistence.listar()){
            if (consulta.getPaciente().getNome().equals(nomePaciente) &&
                    consulta.getPaciente().getProprietario().getCpf().equals(cpfProprietario)) {
                existeConsultaParaOrganizar = true;
                consultasDoPaciente.add(consulta);
            }
        }
        if (existeConsultaParaOrganizar){
            consultasDoPaciente.sort(Comparator.comparing(Consulta::getDataDia).reversed());
        }
        return consultasDoPaciente;
    }

    public Integer totalConsultasMedico(Integer crm){
        int total = 0;
        for (Consulta consulta : consultaPersistence.listar()){
            if (consulta.getMedicoVeterinario().getNumeroRegistro().equals(crm)){
                total++;
            }
        }
        return total;
    }


    /**
     * @param data
     * @return List<Consulta>
     */

    public List<Consulta> consultasMesmoDia(LocalDate data){
        List<Consulta> consultasMesmoDiaPorDataEHora = new ArrayList<>();
        for (Consulta consulta : consultaPersistence.listar()){
            if (consulta.getDataDia().equals(data)){
                existeConsultaParaOrganizar = true;
                consultasMesmoDiaPorDataEHora.add(consulta);
            }
        }
        if (existeConsultaParaOrganizar){
            consultasMesmoDiaPorDataEHora.sort(Comparator.comparing(Consulta::getHora));
        }
        return consultasMesmoDiaPorDataEHora;

    }

}
