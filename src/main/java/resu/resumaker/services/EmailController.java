package resu.resumaker.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class EmailController {
    @PostMapping("/email")
    public void email(@RequestParam String sendTo) throws IOException, AddressException, NoSuchProviderException {
        String localDir = System.getProperty("user.dir");
        localDir = (localDir + "\\resume.pdf");
        File file = new File(localDir);
        EmailService email = new EmailService(file, sendTo);
    }
}