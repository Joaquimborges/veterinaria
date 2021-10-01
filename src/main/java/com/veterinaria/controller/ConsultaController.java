package com.veterinaria.controller;

import com.veterinaria.entity.Consulta;
import com.veterinaria.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "consulta/")
public class ConsultaController {

    @Autowired
   private ConsultaService consultaService;

    @PostMapping(value = "/agendar")
    public ResponseEntity<Consulta> agendar(@RequestBody Consulta consulta){
        return new ResponseEntity<>(consultaService.agendarConsulta(consulta), HttpStatus.CREATED);
    }

    @PutMapping(value = "/alterar")
    public Consulta alterar(@RequestBody Consulta consulta){
        return consultaService.alterar(consulta);
    }


    @GetMapping(value = "/consultasOrdenadoPorDataDecrescente/{nomePaciente}/{cpfProprietario}")
    public List<Consulta> ordenadoPorData(@PathVariable String nomePaciente, @PathVariable String cpfProprietario){
        return consultaService.listarConsultaPorData(nomePaciente, cpfProprietario);
    }

    @GetMapping(value = "/totalConsultasMedico/{crm}")
    public Integer totalConsultasMedico(@PathVariable Integer crm){
        return consultaService.totalConsultasMedico(crm);
    }

    @GetMapping(value = "/consultasMesmoDia/{data}/{nomePaciente}/{cpfProprietario}")
    public List<Consulta> consultasNoMesmoDia(@PathVariable LocalDate data, @PathVariable String nomePaciente, @PathVariable String cpfProprietario){
        return consultaService.consultasMesmoDia(data, nomePaciente, cpfProprietario);
    }
}
