package br.desafiomilionario.agenda.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario {
    @Id
    String email;
    String nome;
    String telefone;
    @OneToOne
    @Nullable
    Agenda agenda;
}
