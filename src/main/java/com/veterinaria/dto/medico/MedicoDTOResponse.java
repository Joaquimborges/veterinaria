package com.veterinaria.dto.medico;


import com.veterinaria.entity.Medico;
import com.veterinaria.entity.Proprietario;

import java.util.ArrayList;
import java.util.List;

public class MedicoDTOResponse
{
    private String nome;
    private String sobrenome;
    private String especialidade;

    public MedicoDTOResponse(String nome, String sobrenome, String especialidade) {

    }

    public static MedicoDTOResponse converte(com.veterinaria.entity.Medico medico)
    {
        return null;
    }

    public static MedicoDTOResponse converter(Medico medico){
        return new MedicoDTOResponse(medico.getNome(), medico.getSobrenome(), medico.getEspecialidade());
    }

    public static List<MedicoDTOResponse> converteLista(List<Medico> medicos){
        List<MedicoDTOResponse> medicoDTOList = new ArrayList<>();
        for (Medico medico : medicos){
            medicoDTOList.add(new MedicoDTOResponse(
                    medico.getNome(), medico.getSobrenome(), medico.getEspecialidade()));
        }
        return medicoDTOList;
    }
}
