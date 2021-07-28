package resu.resumaker;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import resu.resumaker.services.ContactRepository;
import resu.resumaker.userData.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;


// testing imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ResuMakerApplication{
    static void samplePdf()  throws IOException, DocumentException, URISyntaxException {
        // look into checking what the user inputs to see if it's valid (i.e. capitalization) (try/catch and error messages)
        // look @ spring-web when making UI
        // Set contact data
        ContactData contact = new ContactData("Mohamad Saleh", "msaleh@email.com", "747-542-1421");
        // Set education data
        EducationData education = new EducationData("University of California, Santa Cruz",
                "Bachelor of Science: Computer Science", "4.0", "Santa Cruz, CA", "2018-09 - 2022-06");

        // Set skill data
        SkillsData skill = new SkillsData();
        skill.setSkills("Java");
        skill.setSkills("C");
        skill.setSkills("Git");
        skill.setSkills("HTML/CSS");

        // Set work data
        WorkData work = new WorkData("Software Engineer Intern", "Google", "05/2020 - Present", "San Jose, CA", "Worked on the Google Maps team.");

        // Set custom field        // Create & open document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("sample.pdf"));
        document.open();

        // Setting fonts
        Font title = FontFactory.getFont(FontFactory.HELVETICA, 24, BaseColor.BLACK);
        Font subtitle = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
        Font header = FontFactory.getFont(FontFactory.HELVETICA, 20, BaseColor.BLACK);
        Font paragraph = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        // Create chunks
        Chunk nameData = new Chunk(contact.getName(), title);
        Chunk contactData = new Chunk(contact.getEmail() + " | " + contact.getPhone(), subtitle);
        Chunk newLine = new Chunk("\n",paragraph);
        Chunk educationHeader = new Chunk("Education", header);
        Chunk skillsHeader = new Chunk("Skills", header);
        Chunk workHeader = new Chunk("Work Experience", header);
        Chunk educationData = new Chunk( education.getDegree() + " - " + education.getGpa() +
                " GPA\n" + education.getSchool() + " - " + education.getLocation() +"\nDates Attended: " + education.getDates(), paragraph);

        String skillsList = (String) skill.getSkills().get(0);
        if (skill.getSkills().size() > 1) {
            for (int i = 1; i < skill.getSkills().size(); i++) {
                skillsList = skillsList + ", " + (String) skill.getSkills().get(i);
            }
        }

        Chunk skillDataChunk = new Chunk(skillsList, paragraph);
        Chunk workDataChunk = new Chunk(work.getTitle() + " - " + work.getDates() + "\n" + work.getCompany() + " - " + work.getLocation() + "\n" + work.getDescription(), paragraph);

        // Create paragraphs
        Paragraph name = new Paragraph(nameData);
        name.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(name);

        Paragraph spacing = new Paragraph(newLine);
        Paragraph contactInfo = new Paragraph(contactData);
        contactInfo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(contactInfo);
        document.add(spacing);

        Paragraph eduHeader = new Paragraph(educationHeader);
        Paragraph eduData = new Paragraph(educationData);

        Paragraph skillHead = new Paragraph(skillsHeader);
        Paragraph skillDataPara = new Paragraph(skillDataChunk);

        Paragraph workHead = new Paragraph(workHeader);
        Paragraph workDataPara = new Paragraph(workDataChunk);

        document.add(eduHeader);
        document.add(eduData);
        document.add(spacing);
        document.add(skillHead);
        document.add(skillDataPara);
        document.add(spacing);
        document.add(workHead);
        document.add(workDataPara);
        document.add(spacing);
        document.add(spacing);


        document.close();
    }
    public static void main(String[] args) throws IOException, DocumentException, URISyntaxException {
        SpringApplication.run(ResuMakerApplication.class, args);
        //samplePdf();



    }
    private static final Logger log = LoggerFactory.getLogger(ResuMakerApplication.class);

    @Bean
    public CommandLineRunner demo(ContactRepository repository) {
        return (args) -> {
            // save a few customers
//            repository.save(new ContactData("Jack Bauer", "jbauer@blahblah.com", "12345678980"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (ContactData contact : repository.findAll()) {
                log.info(contact.toString());
            }
            log.info("");
        };
    }


}

