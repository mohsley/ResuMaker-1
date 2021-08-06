package resu.resumaker;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import resu.resumaker.services.ContactRepository;
import resu.resumaker.services.PDFService;
import resu.resumaker.services.SubmitController;
import resu.resumaker.userData.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

// testing imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ResuMakerApplication{

    public static void main(String[] args) throws IOException, DocumentException, URISyntaxException, SQLException {
        SpringApplication.run(ResuMakerApplication.class, args);
    }

//    static void testCall() {
//        String [] sampleContact = {"Mohamad Saleh", "mohs@outlook.com", "707-414-2323"};
//        String [] sampleSkills = {"C++", "Git"};
//        String [] sampleWork = {"CodeLabs SWE Intern", "CodeDay", "06/2021 - 08/2021", "Remote", "Made a resume maker"};
//        String [] sampleEducation = {"Univeristy of Santa Cruz", "Bachelor of Science", "4.0", "Santa Cruz, CA", "09/2020 - 06/2022"};
//        SubmitController.contactSubmit(sampleContact);
//        SubmitController.skillSubmit(sampleSkills);
//        SubmitController.workSubmit((sampleWork));
//        SubmitController.educationSubmit(sampleEducation);
//    }
}

