package org.test.teamproject_back.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqSendMailDto;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String senderEmail;
    @Autowired
    private JavaMailSender javaMailSender;
    private String verifyCode;

    public void sendEmail(ReqSendMailDto dto) throws Exception {
        verifyCode = String.format("%.0f",(Math.random() * (90000)) + 100000);
        try {
            StringBuilder htmlContent = new StringBuilder();
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");

            htmlContent.append("<h3>요청하신 인증 번호입니다.</h3>");
            htmlContent.append("<h1>");
            htmlContent.append(verifyCode);
            htmlContent.append("</h1>");
            htmlContent.append("<h3>감사합니다.</h3>");

            mail.setText(htmlContent.toString(), "UTF-8", "html");

            mailHelper.setFrom(senderEmail);
            mailHelper.setTo(dto.getToEmail());
            mailHelper.setSubject("인증 번호 안내");
            mailHelper.setText(htmlContent.toString(), true);

            javaMailSender.send(mail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean authEmail(String checkNum) {

        if (checkNum.equals(verifyCode)) {
            return true;
        }
        return false;
    }
}
