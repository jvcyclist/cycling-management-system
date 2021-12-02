package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karas.cyclingmanagementsystem.service.EmailSenderService;

@RestController
@RequestMapping
public class MailSendTest {

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("/mail")
    public boolean sendMail(){

        emailSenderService.sendEmail("patryk1karas@gmail.com", "Testowy temat", "Testowy content!!!");

        return true;
    }


}
