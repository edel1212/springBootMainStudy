package com.yoo.springBootMailStudy.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    @DisplayName("이메일 전송")
    void simpleMailSender() {
        emailService.mailSend();
    }

    @Test
    @DisplayName("HMLT 본문 및 이미지 첨부")
    void mailToHTML() {
        emailService.mailSendToHTMLAddFile();
    }
}