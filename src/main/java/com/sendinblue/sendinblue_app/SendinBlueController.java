package com.sendinblue.sendinblue_app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendinBlueController {

    private final SendinBlueService sendinBlueService;



    @PostMapping("/")
    public ResponseEntity<String> sendMail(@RequestBody SendMailRequest sendMailRequest){
        return ResponseEntity.ok(sendinBlueService.sendMail(sendMailRequest));
    }


}
