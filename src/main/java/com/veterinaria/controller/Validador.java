package com.veterinaria.controller;

import com.veterinaria.dto.medico.MedicoDTOResponse;

import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;
import java.util.Locale;

public class Validador {

    //classe validadora de campos de inserção


    private enum especialidades {
        ANIMAL_DOMESTICO,
        ANIMAL_FAZENDA,
        ANIMAL_SELVAGEM,
        PLENA
    }

    private enum tipoAnimal {
        DOMESTICO("Doméstico"),
        FAZENDA("Fazenda"),
        SELVAGEM("Selvagem");

        private String tipo;

        private tipoAnimal(String tipo) {
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }

        public String getTipoParaComparacao() {
            return tipo.toLowerCase(Locale.ROOT);
        }


    }

    private enum tipoEspecialidade {
        DOMESTICO("doméstico"),
        FAZENDA("fazenda"),
        SELVAGEM("selvagem"),
        DOMESTICO_FAZENDA("domésticos e fazenda"),
        PLENA("todos");


        private String tipo;

        private tipoEspecialidade(String tipo) {
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }


    }


    public boolean cpfValido(String cpf) {

        boolean matchesPontoTraco = cpf.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}");
        boolean matchesSoNumero = cpf.matches("[0-9]{11}");
        final int digitoscpf = 11;

        int soma_9prim = 0;
        int soma_10prim = 0;
        int soma_11dig = 0;

        String result = cpf;
        int[] vetorcpf;

        if (matchesPontoTraco) {
            result = result.replace(".", "");
            result = result.replace("-", "");
        }

        if (!matchesSoNumero) {
            System.out.println("Nem tem a quantidade certa de caracteres para um cpf!\n");
            return false;
        }


        //tratando o cpf

        vetorcpf = result.chars().map(x -> x - '0').toArray();

        int fact = 10;
        for (int i = 0; i < 9; i++) {
            soma_9prim += fact * vetorcpf[i];
            fact--;
        }


        if (vetorcpf[9] != ((soma_9prim * 10) / 11) % 10) {
            //throw cpf invalido

            System.out.println("confira o final do seu cf: " + vetorcpf[9] + "  " + soma_9prim / 11 % 10);
            return false;
        }

        fact = 11;
        for (int i = 0; i < 9; i++) {
            soma_10prim += fact * vetorcpf[i];
            fact--;
        }

        if (vetorcpf[10] != ((soma_10prim * 10) / 11) % 10) {
            System.out.println("trocou o último dígito, né?" + vetorcpf[10] + "  " + soma_10prim / 11 % 10);
            return false;
        }

        System.out.println("CPF validado com sucesso!");
        return true;

    }


    public boolean validaEspecialidade(String especialidade) {
        try {
            especialidades.valueOf(especialidade);
            return true;
        } catch (IllegalArgumentException il) {
            return false;
        }

    }

    public boolean validaTipoAnimal(String animal) {
        try {
            tipoAnimal.valueOf(animal);
            return true;
        } catch (IllegalArgumentException il) {

        }
        return false;
    }

}