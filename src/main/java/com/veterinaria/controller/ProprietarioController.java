package com.veterinaria.controller;

import com.veterinaria.dto.proprietario.ProprietarioDTO;
import com.veterinaria.dto.proprietario.ProprietarioRequest;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "proprietario/")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ProprietarioDTO> cadastrar(@RequestBody ProprietarioRequest proprietarioRequest){
        Proprietario proprietario = proprietarioService.cadastrar(proprietarioRequest.converte());
        return new ResponseEntity<>(ProprietarioDTO.converter(proprietario), HttpStatus.CREATED);
    }

    @GetMapping(value = "/obter/{c}")
    public ResponseEntity<ProprietarioDTO> get(@PathVariable("c") String cpf){
        Proprietario proprietario = proprietarioService.getProprietario(cpf);
        return new ResponseEntity<>(ProprietarioDTO.converter(proprietario), HttpStatus.FOUND);
    }

    @GetMapping(value = "/listar")
    public List<ProprietarioDTO> listar(){
        List<Proprietario> proprietarios = proprietarioService.Listar();
        return ProprietarioDTO.converteLista(proprietarios);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<ProprietarioDTO> editar(@RequestBody ProprietarioRequest proprietarioRequest){
        Proprietario proprietario = proprietarioService.altera(proprietarioRequest.converte());
        return new ResponseEntity<>(ProprietarioDTO.converter(proprietario), HttpStatus.ACCEPTED);
    }


    @DeleteMapping(value = "/remover/{c}")
    public ResponseEntity<Boolean> remover(@PathVariable("c") String cpf){
        return new ResponseEntity<>(proprietarioService.apagar(cpf), HttpStatus.GONE) ;
    }

}
