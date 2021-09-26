package com.veterinaria.controller;

import com.veterinaria.dto.medico.MedicoDTORequest;
import com.veterinaria.dto.medico.MedicoDTOResponse;
import com.veterinaria.entity.Medico;
import com.veterinaria.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "medico/")
public class MedicoController
{


    @Autowired
    private MedicoService medicoService;


    @PostMapping(value = "/cadastrar")
    public ResponseEntity<MedicoDTOResponse> cadastrar(@RequestBody MedicoDTORequest medicoRequest)
    {
        Medico medico = medicoService.cadastrar(medicoRequest.converte(medicoRequest));
        return new ResponseEntity<>(MedicoDTOResponse.converte(medico), HttpStatus.CREATED);
    }

    @GetMapping(value = "/obter/{crvet}")
    public MedicoDTOResponse get(@PathVariable("crvet") String crvet){
        Medico medico = medicoService.getMedico(Integer.valueOf(crvet));
        return MedicoDTOResponse.converte(medico);
    }

    @GetMapping(value = "/listar")
    public List<MedicoDTOResponse> listar(){

        return MedicoDTOResponse.converteLista(medicoService.Listar());
    }

    @PutMapping(value = "/editar")
    public MedicoDTOResponse editar(@RequestBody MedicoDTORequest medicoDTORequest){
        Medico medico = medicoService.altera(medicoDTORequest.converte(medicoDTORequest));
        return MedicoDTOResponse.converter(medico);
    }

    @DeleteMapping(value = "/remover/{c}")
    public boolean remover(@PathVariable("c") String crvet){
        return medicoService.apagar(crvet);
    }

}
