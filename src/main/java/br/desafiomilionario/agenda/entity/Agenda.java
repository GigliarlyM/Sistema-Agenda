package br.desafiomilionario.agenda.entity;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Agenda {
    List<Compromisso> compromissos;
}
