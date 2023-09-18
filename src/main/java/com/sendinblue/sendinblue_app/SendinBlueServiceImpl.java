package com.sendinblue.sendinblue_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.util.List;

@Service
public class SendinBlueServiceImpl implements SendinBlueService{

    @Value("${sendinblue.mail.api_key}")
    private String apiKey;


    @Override
    public String sendMail(SendMailRequest sendMailRequest) {
        TransactionalEmailsApi transactionalEmailsApi = new TransactionalEmailsApi(createUser());
        try{
            transactionalEmailsApi.sendTransacEmail(creatEmail(sendMailRequest));
        }catch (ApiException e){
            System.out.println("Error " + e.getResponseBody());
        }
        return "Sent successfully";
    }

    private SendSmtpEmail creatEmail(SendMailRequest sendMailRequest) {
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();

        SendSmtpEmailTo sendSmtpEmailTo = new SendSmtpEmailTo();
        sendSmtpEmailTo.setEmail(sendMailRequest.getTo());
        sendSmtpEmailTo.setName(sendMailRequest.getName());

        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail("learnspace@learnspace.africa"); // Replace with your sender's email address
        sender.setName("LearnSpace");     // Replace with your sender's name
        sendSmtpEmail.setSender(sender);
        sendSmtpEmail.setTo(List.of(sendSmtpEmailTo));
        sendSmtpEmail.setSubject(sendMailRequest.getSubject());
        sendSmtpEmail.setTextContent(sendMailRequest.getContent());
        return sendSmtpEmail;
    }

    private ApiClient createUser(){
        ApiClient apiClient = Configuration.getDefaultApiClient();
        apiClient.setApiKey(apiKey);
        return apiClient;
    }
}
