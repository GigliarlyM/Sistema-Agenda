package br.desafiomilionario.agenda.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_compromisso")
@Data
public class Compromisso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titulo;
    Date dataHora;
    String local;
    Boolean status;
    @ManyToOne
    @JoinColumn(name = "agenda_id")
    Agenda agenda;
    @OneToMany
    List<Notificacao> notificacoes;
}
