package br.desafiomilionario.agenda.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmationEmail(String toEmail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Confirme sua conta!");

        String confirmationUrl = "http://localhost:8080/auth/confirm-account?token=" + token;
        message.setText("Para confirmar sua conta, clique no link: " + confirmationUrl + "\n\nEste link expirara em 24 horas.");

        mailSender.send(message);
        System.out.println("E-mail de confirmacao enviado para: " + toEmail); //debugzera
    }
}
