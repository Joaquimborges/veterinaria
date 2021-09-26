package com.veterinaria.dto.medico;

import com.veterinaria.entity.Medico;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicoDtoRequest {

    private String nome;
    private String sobrenome;
    private String cpf;
    private Integer numeroRegistro;
    private String especialidade;

    public MedicoDtoRequest() {
    }

    public MedicoDtoRequest(String nome, String sobrenome, String cpf, Integer numeroRegistro, String especialidade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.numeroRegistro = numeroRegistro;
        this.especialidade = especialidade;
    }

    public Medico converte(){
        return new Medico(nome, sobrenome, cpf, numeroRegistro, especialidade);
    }
}
