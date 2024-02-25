package com.yoo.springBootMailStudy.mail;

import lombok.Data;

@Data
public class MailDTO {
    private String address;  // 받는사람
    private String subject;  // 제목
    private String text;     // 내용
}
