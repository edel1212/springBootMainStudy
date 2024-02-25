package com.yoo.springBootMailStudy.service;

import com.yoo.springBootMailStudy.mail.MailDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    @DisplayName("이메일 전송")
    void name() {
        emailService.mailSend();
    }
}