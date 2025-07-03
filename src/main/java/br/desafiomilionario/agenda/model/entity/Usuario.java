package br.desafiomilionario.agenda.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario {
    @Id
    String email;
    String nome;
    String telefone;
    boolean enabled = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_email"))
    @Column(name = "role")
    Set<String> roles;

    @OneToOne
    @Nullable
    Agenda agenda;
}
