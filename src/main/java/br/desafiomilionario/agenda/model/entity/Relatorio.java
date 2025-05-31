package br.desafiomilionario.agenda.model.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_relatorio")
@Data
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date dataInicio;
    Date dataFim;
}
