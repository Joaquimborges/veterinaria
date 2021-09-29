package com.veterinaria.controller;

import com.veterinaria.dto.medico.MedicoDTO;
import com.veterinaria.dto.medico.MedicoDtoRequest;
import com.veterinaria.entity.Medico;
import com.veterinaria.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "medico/")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<MedicoDTO> cadastrar(@RequestBody MedicoDtoRequest medicoDtoRequest){
        Medico medico = medicoService.cadastrar(medicoDtoRequest.converte());
        return new ResponseEntity<>(MedicoDTO.converter(medico), HttpStatus.CREATED);
    }

    @GetMapping(value = "/obter/{crm}")
    public ResponseEntity<MedicoDTO> obterUm(@PathVariable Integer crm){
        Medico medico = medicoService.getMedico(crm);
        return new ResponseEntity<>(MedicoDTO.converter(medico), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/listar")
    public List<MedicoDTO> listar(){
        List<Medico> medicos = medicoService.listar();
        return MedicoDTO.converterLista(medicos);
    }

    @PutMapping(value = "/alterar")
    public ResponseEntity<MedicoDTO> alterar(@RequestBody MedicoDtoRequest medicoDtoRequest){
        Medico medico = medicoService.alterar(medicoDtoRequest.converte());
        return new ResponseEntity<>(MedicoDTO.converter(medico), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/apagar/{crm}")
    public ResponseEntity<Boolean> apagar(@PathVariable Integer crm){
        return new ResponseEntity<>(medicoService.apagar(crm), HttpStatus.OK);
    }
}
