package com.veterinaria.dto.medico;

import com.veterinaria.controller.Validador;
import com.veterinaria.entity.Medico;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.validation.Validator;

@Getter
@Setter
public class MedicoDTORequest
{
    /*
        Criação da classe MedicoRequest

            -> Está classe deve ser criado na package dto.
            -- Atributos
            -cpf: String
            -nome: String
            -sobrenome: String
            -especialidade: String
            -numeroRegistro: String -> Integer, certo?

            Criar método toString() utilizando a sobreescrita @Override

            Estas classes devem conter os atributos que forem escolhidos para expor no request.
            Deve conter tb os métodos de conversão de DTO para objetoClasse
     */

    private String nome;
    private String sobrenome;
    private String cpf;
    private Integer crvet;
    private String especialidade;

    @Autowired
    Validador validator;

    public MedicoDTORequest(){}

    public MedicoDTORequest(String nome, String sobrenome, String cpf, Integer crvet, String especialidade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.crvet = crvet;
        this.especialidade = especialidade;
    }

    public Medico converte (MedicoDTORequest medico)
    {
        boolean valida = validator.cpfValido(medico.cpf) && validator.validaEspecialidade(medico.especialidade) && validator.validaEspecialidade(medico.especialidade);
        if (valida) return new Medico(nome, sobrenome, cpf, crvet, especialidade);
        return null;
    }








    @Override
    public String toString() {
        return "{\"MedicoRequest\":{"
                + "                        \"nome\":\"" + nome + "\""
                + ",                         \"sobrenome\":\"" + sobrenome + "\""
                + ",                         \"cpf\":\"" + cpf + "\""
                + ",                         \"crvet\":\"" + crvet + "\""
                + ",                         \"especialidade\":\"" + especialidade + "\""
                + "}}";
    }
}