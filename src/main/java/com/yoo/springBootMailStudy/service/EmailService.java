package com.yoo.springBootMailStudy.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    public void mailSendToHTML() {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo("edel1212@naver.com");
            helper.setSubject("와구와구과구!!!");

            // HTML 콘텐츠를 문자열로 작성
            String htmlContent = "<h1>안녕하세요, 흑곰님입니다!</h1>" +
                    "<p>!!!! 내용입니다.</p>" +
                    "<p>HTML 형식의 <strong>이메일</strong>을 보내드립니다.</p>";

            helper.setText(htmlContent, true); // true를 설정하여 HTML 메일로 인식하게 함

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mailSendToHTMLAddFile(){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // multipart 모드 활성화

            helper.setTo("edel1212@naver.com");
            helper.setSubject("흑곰님입니다.");

            // HTML 콘텐츠를 문자열로 작성
            String htmlContent = "<img src='cid:logo'>"; // 이미지를 참조하는 HTML 태그

            helper.setText(htmlContent, true); // true를 설정하여 HTML 메일로 인식하게 함

            // 이미지 파일을 첨부
            ClassPathResource image = new ClassPathResource("static/img/test.jpg");
            helper.addInline("logo", image);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
