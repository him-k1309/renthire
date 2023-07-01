package com.lumentech.renthire.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    public EmailServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    @Override
    public void sendRegistrationEmail(String recipientEmail, String userName) {
        String subject = "Registration Successful";
        String body = "Dear "+userName+",\n\n"
                +"Congratulations! Your registration has been successful.\n"
                +"Thank you for joining Renthire.\n\n"
                +"Best Regards,\n"
                +"Your Renthire Team";
        sendEmail(recipientEmail, subject, body);
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
