# SMTP 테스트

- gmail을 기반으로 테스트
- 스큐리티 적용 X
- 테스트용이기에 Interface 구조를 사용하지 않고 구현
- 서버 로직 구현전 Google에서 앱 비밀번호 설정이 필요함
  - google → 계정관리 → 보안 → 2단계 인증 → 앱 비밀번호 설정
- 순수 메일 발송 테스트
  - 일반 메세지만 보내기
  - HTML코드 형식으로 보내기
  - 파일을 첨부하여 보니rl

### 사용 방법
- 의존성 추가
  - `implementation 'org.springframework.boot:spring-boot-starter-mail'`
- SMTP 대상 서버 사용 설정
    ```properties
    # Mail Setting
  mail:
    host: smtp.gmail.com # 1
    port: 587 # 2
    username: {계정정보} # 3
    password: {패스워드} # 4
    properties:
      mail:
        smtp:
          auth: true # 5
          timeout: 5000 # 6
          starttls:
            enable: true # 7
    ```
- 비즈니스 로직
  ```java
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
              String htmlContent = "<h1>안녕하세요, 흑곰님입니다!</h1>" +
                      "<p>!!!! 내용입니다.</p>" +
                      "<p>HTML 형식의 <strong>이메일</strong>을 보내드립니다.</p>"; // 이미지를 참조하는 HTML 태그
  
              helper.setText(htmlContent, true); // true를 설정하여 HTML 메일로 인식하게 함
  
              // 이미지 파일을 첨부
              ClassPathResource image = new ClassPathResource("static/img/test.jpg");
              helper.addInline("test.png", image);
  
              javaMailSender.send(mimeMessage);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  
  
  }
    ```
  
