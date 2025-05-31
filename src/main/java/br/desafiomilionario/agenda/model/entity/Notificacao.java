package br.desafiomilionario.agenda.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_notificicao")
@Data
public class Notificacao {
    @Id
    Long id;
    String mensagem;
    Date dataEnvio;
    String tipoNotificacao;
}
