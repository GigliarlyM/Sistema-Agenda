package br.desafiomilionario.agenda.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
}
