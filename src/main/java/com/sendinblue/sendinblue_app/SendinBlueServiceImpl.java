package com.sendinblue.sendinblue_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class SendinBlueServiceImpl implements SendinBlueService {

    @Value("${sendinblue.mail.api_key}")
    private String apiKey;

    @Value("${sendinblue.mail.url}")
    private String mailUrl;

    @Value("${app.name}")
    private String appName;

    @Value("${app.email}")
    private String appEmail;


    @Override public String sendMail(SendMailRequest sendMailRequest) {

        WebClient.builder().baseUrl(mailUrl).defaultHeader("api-key", apiKey).build()
                .post().bodyValue(creatEmailRequest(sendMailRequest)).retrieve().bodyToMono(String.class).block() ;
        return "Email sent Successfully";
    }

    private EmailRequest creatEmailRequest(SendMailRequest sendMailRequest){
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setSender(creatInfo(appEmail, appName));
        emailRequest.setSubject(sendMailRequest.getSubject());
        emailRequest.setTo(List.of(creatInfo(sendMailRequest.getTo(), sendMailRequest.getName())));
        emailRequest.setHtmlContent(sendMailRequest.getContent());
        return emailRequest;
    }

    private MailInfo creatInfo(String to, String name){
        MailInfo mailInfo = new MailInfo();
        mailInfo.setEmail(to);
        mailInfo.setName(name);
        return mailInfo;
    }


}
