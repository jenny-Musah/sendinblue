package com.sendinblue.sendinblue_app;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMailRequest {
    private String to;
    private String name;
    private String content;
    private String subject;

}
