package com.example.demo.mail;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@RestController
public class EmailController {
    private final JavaMailSender javaMailSender;

    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String fromEmail;

    @PostMapping(value = "/send-email", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendEmail(
            //@RequestParam("recipient") String recipient,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message
    ) {
        try {
            MimeMessage emailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(emailMessage, true, "UTF-8"); // Здесь задаем кодировку
            helper.setFrom(fromEmail);
            //helper.setTo(recipient);
            helper.setTo("petinv@i.ua");
            helper.setSubject(subject);
            helper.setText(message, true);

            javaMailSender.send(emailMessage);
            return "Email успешно отправлен!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Ошибка при отправке email!";
        }
    }
}