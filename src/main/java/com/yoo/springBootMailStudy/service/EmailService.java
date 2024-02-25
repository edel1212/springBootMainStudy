package com.yoo.springBootMailStudy.service;

import com.yoo.springBootMailStudy.mail.MailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void mailSend() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("edel1212@naver.com");
        msg.setSubject("흑곰님입니다.");  // 제목
        msg.setText("!!!! 내용입니다."); //  내용
        javaMailSender.send(msg);
    }

}
