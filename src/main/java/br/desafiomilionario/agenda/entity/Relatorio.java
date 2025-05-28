package br.desafiomilionario.agenda.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Relatorio {
    Date dataInicio;
    Date dataFim;
    List<Compromisso> compromissosRealizados;
    List<Compromisso> compromissosPendentes;
}
