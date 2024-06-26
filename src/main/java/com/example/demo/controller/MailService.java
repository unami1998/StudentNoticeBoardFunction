package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class MailService {
    @Autowired
    private JavaMailSender emailsender;
    @Autowired
    private StudentRepository studentRepository;

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String resetPasswordAndSend() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public MimeMessage createMessage(String email) throws MessagingException, UnsupportedEncodingException {
//		System.out.println("보내는 대상 : " + to);
//		System.out.println("인증 번호 : " + ePw);

        String randomPassword = resetPasswordAndSend();
        MimeMessage message = emailsender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, email);// 보내는 대상
        message.setSubject("[대나무 숲] 사용자 계정 - 비밀번호 임시 변경입니다.");// 제목

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>이메일 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "임시비밀번호는 : <strong>";
        msgg += randomPassword + "</strong><div><br/> "; // 메일에 인증번호 넣기 //나중에 성공하면 email이랑 링크 추가하기
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("fpahs927@naver.com", "대나무숲 관리자"));// 보내는 사람
        Student student = studentRepository.findByEmail(email);
        student.setPassword(randomPassword);
        studentRepository.save(student);
        return message;
    }

    public long sendSimpleMessage(String email) throws MessagingException, UnsupportedEncodingException {
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("이메일이 존재하지않습니다.");

        MimeMessage message = createMessage(email); // 메일 발송

        emailsender.send(message);
        return 1;

    }
}
