package com.veterinaria.dto.proprietario;


import com.veterinaria.entity.Proprietario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProprietarioRequest {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String endereco;
    private String telefoneContato;
    private LocalDate dataDeNascimento;

    public ProprietarioRequest() {
    }

    public ProprietarioRequest(String nome, String sobrenome, String cpf, String endereco, String telefoneContato, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefoneContato = telefoneContato;
        this.dataDeNascimento = dataDeNascimento;
    }

    public Proprietario converte(){
        return new Proprietario(nome, sobrenome, cpf, endereco, telefoneContato, dataDeNascimento);
    }
}
