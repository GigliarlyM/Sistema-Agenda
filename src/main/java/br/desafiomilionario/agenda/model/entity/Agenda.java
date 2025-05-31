package br.desafiomilionario.agenda.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_agenda")
@Data
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany
    List<Compromisso> compromissos;
    @OneToMany
    List<Relatorio> relatorios;
    @OneToOne
    Usuario usuario;
}
