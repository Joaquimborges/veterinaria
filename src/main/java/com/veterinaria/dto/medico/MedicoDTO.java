package com.veterinaria.dto.medico;

import com.veterinaria.entity.Medico;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MedicoDTO {

    private String nome;
    private String sobrenome;
    private Integer numeroRegistro;
    private String especialidade;

    public MedicoDTO() {
    }

    public MedicoDTO(String nome, String sobrenome, Integer numeroRegistro, String especialidade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.numeroRegistro = numeroRegistro;
        this.especialidade = especialidade;
    }

    public static MedicoDTO converter(Medico medico){
        return new MedicoDTO(medico.getNome(), medico.getSobrenome(), medico.getNumeroRegistro(), medico.getEspecialidade());
    }

    public static List<MedicoDTO> converterLista(List<Medico> medicos){
        List<MedicoDTO> medicoDTOList = new ArrayList<>();
        for (Medico medico : medicos){
            medicoDTOList.add(new MedicoDTO(medico.getNome(), medico.getSobrenome(), medico.getNumeroRegistro(), medico.getEspecialidade()));
        }
        return medicoDTOList;
    }
}
