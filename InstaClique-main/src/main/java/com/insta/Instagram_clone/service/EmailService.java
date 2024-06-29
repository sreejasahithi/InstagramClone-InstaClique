package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendAccountDisabledEmail(String userEmail, String userId, String username) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(userEmail);
            helper.setSubject("Your account for instagram_clone has been disabled");
            helper.setText("Dear " + username + ",\n\nYour account with ID " + userId
                    + " has been disabled. Please contact support for further assistance.\n\nRegards,\nThe Management Team");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    public void sendAccountEnabledEmail(String userEmail, String userId, String username) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(userEmail);
            helper.setSubject("Your account for instagram_clone has been enabled");
            helper.setText("Dear " + username + ",\n\nYour account with ID " + userId
                    + " has been enabled. You can now login to your account.\n\nRegards,\nThe Management Team");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
