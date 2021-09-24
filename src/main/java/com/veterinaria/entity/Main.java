package com.veterinaria.entity;

import java.time.LocalDate;

public class Main {

    LocalDate data = LocalDate.of(1997, 11, 7);
    LocalDate dataAnimal = LocalDate.of(2021, 2, 7);

    Medico medico = new Medico("José", "Maria", "gggggggggjh",10336, "cardio");

    Proprietario proprietario = new Proprietario("Matheus", "Willock", "fffgggkkkml", "rua erotides", "34343434", data);

    Paciente paciente = new Paciente("cachorro", "Branco", "Akita", "Tobirama", "Macho", dataAnimal, proprietario);

}
