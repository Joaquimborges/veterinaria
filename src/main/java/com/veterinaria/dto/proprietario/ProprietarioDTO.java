package com.veterinaria.dto.proprietario;

import com.veterinaria.entity.Proprietario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProprietarioDTO {

    private String nome;
    private String sobrenome;
    private String endereco;

    public ProprietarioDTO() {
    }

    public ProprietarioDTO(String nome, String sobrenome, String endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
    }

    public ProprietarioDTO converter(Proprietario proprietario){
        return new ProprietarioDTO(proprietario.getNome(), proprietario.getSobrenome(), proprietario.getEndereco());
    }

    public List<ProprietarioDTO> converteLista(List<Proprietario> proprietarios){
        List<ProprietarioDTO> proprietarioDTOList = new ArrayList<>();
        for (Proprietario proprietario : proprietarios){
            proprietarioDTOList.add(new ProprietarioDTO(
                    proprietario.getNome(), proprietario.getSobrenome(), proprietario.getEndereco()));
        }
        return proprietarioDTOList;
    }

}
