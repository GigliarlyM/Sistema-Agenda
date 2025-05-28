package br.desafiomilionario.agenda.entity;

import jakarta.persistence.Entity;

@Entity
public class Usuario {
    String nome;
    String email;
    String telefone;
}
