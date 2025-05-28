package br.desafiomilionario.agenda.entity;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Notificacao {
    String mensagem;
    Date dataEnvio;
    String tipoNotificacao;
}
