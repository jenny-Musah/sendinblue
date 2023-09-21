package com.sendinblue.sendinblue_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Collections;
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
        emailRequest.setTo(Collections.singleton(creatInfo(sendMailRequest.getTo(), sendMailRequest.getName())));
        emailRequest.setHtmlContent("<h2>Hi <td> th:text= \"${firstName}\" </td> </h2>\n" +
                "\n" +
                "<p>You have been invited to learnspace for the role of an admin</p>\n" +
                "\n" +
                "<p>Kindly click on the link below to accept the invitation and get started today</p>\n" +
                "\n" +
                "<p><a href=\"www.google.com?invitation-token={invitationToken}\">Accept Invitation<a/></p>");
        return emailRequest;
    }

    private MailInfo creatInfo(String to, String name){
        MailInfo mailInfo = new MailInfo();
        mailInfo.setEmail(to);
        mailInfo.setName(name);
        return mailInfo;
    }


}
