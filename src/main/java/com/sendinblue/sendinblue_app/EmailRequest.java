package com.sendinblue.sendinblue_app;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class EmailRequest {

    private MailInfo sender;

    private Collection<MailInfo> to;
    private String subject;
    private String htmlContent;
}
