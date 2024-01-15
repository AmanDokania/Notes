**Spring provides support of Java Mail sender using this dependency we can trigger a mail.**

```Java
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-mail</artifactId>  
</dependency>
```

we need to do some configuration in our application.yaml file 
```Java
spring:  
  mail:  
    host: smtp.gmail.com  
    port: 587  
    username: <YOUR_EMAIL>  
    password: <YOUR_PWD>  
    properties:  
      mail:  
        smtp:  
          auth: true  
          starttls:  
            enable: true
```

there are some examples that how we can send a simple email or email with attachment

```Java
package com.javatechie.service;  
  
import com.javatechie.dto.EmailRequest;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.core.io.FileSystemResource;  
import org.springframework.mail.SimpleMailMessage;  
import org.springframework.mail.javamail.JavaMailSender;  
import org.springframework.mail.javamail.MimeMessageHelper;  
import org.springframework.stereotype.Service;  
  
import javax.mail.MessagingException;  
import javax.mail.internet.MimeMessage;  
import java.io.File;  
  
@Service  
public class EmailService {  
  
    @Autowired  
    private JavaMailSender javaMailSender;  
  
    @Value("${spring.mail.username}")  
    private String sender;  
  
  
    public String sendSimpleEmail(EmailRequest request) {  
        SimpleMailMessage mailMessage = new SimpleMailMessage();  
        mailMessage.setFrom(sender);  
        mailMessage.setTo(request.getToEMail());  
        mailMessage.setSubject(request.getSubject());  
        mailMessage.setText(request.getMessageBody());  
        javaMailSender.send(mailMessage);  
        return "email successfully send to : " + request.getToEMail();  
    }  
  
  
    public String sendEmailWithAttachment(EmailRequest request) throws MessagingException {  
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();  
  
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);  
  
        helper.setFrom(sender);  
        helper.setTo(request.getToEmails());  
        helper.setSubject(request.getSubject());  
        helper.setText(request.getMessageBody());  
  
        FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));  
        helper.addAttachment(file.getFilename(), file);  
  
        javaMailSender.send(mimeMessage);  
        return "Mail sent successfully with attachment " + file.getFilename();  
    }  
}
```
