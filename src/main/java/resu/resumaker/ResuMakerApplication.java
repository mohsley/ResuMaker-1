package resu.resumaker;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import userData.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication

public class ResuMakerApplication {

    public static void main(String[] args) throws IOException, DocumentException, URISyntaxException {
        // look into checking what the user inputs to see if it's valid (i.e. capitalization) (try/catch and error messages)
        // look @ spring-web when making UI
        SpringApplication.run(ResuMakerApplication.class, args);

        // Set contact data
        ContactData contact = new ContactData();
        contact.setName("Mohny Tonhamad");
        contact.setEmail("mohamad@tony.kevin.au.com");
        contact.setPhoneNumber("000-000-0000");

        // Set education data
        EducationData education = new EducationData();
        education.setSchoolName("University of California, Santa Cruz");
        education.setDegreeAndMajor("Bachelor of Science: Computer Science");
        education.setGPA("4.0");
        education.setLocation("Santa Cruz, CA");
        education.setDates("2032-09 - 2034-06");

        // Set skill data
        SkillsData skill = new SkillsData();
        skill.setSkills("Java");
        skill.setSkills("C");
        skill.setSkills("Git");
        skill.setSkills("HTML/CSS");

        // Set work data
        WorkData work = new WorkData("Comedian", "Fang Co.", "05/2020 - Present", "Santa Cruz, CA", "Made and created cool jokes at a Fang company.");

        // Set custom fields
        CustomData custom = new CustomData();
        custom.setTitle("Custom Section");
        custom.setBody("Lorem ipsum");

        // Create & open document
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
        Chunk contactData = new Chunk(contact.getEmail() + " | " + contact.getPhoneNumber(), subtitle);
        Chunk newLine = new Chunk("\n",paragraph);
        Chunk educationHeader = new Chunk("Education", header);
        Chunk skillsHeader = new Chunk("Skills", header);
        Chunk workHeader = new Chunk("Work Experience", header);
        Chunk customHeader = new Chunk(custom.getTitle(), header);
        Chunk educationData = new Chunk( education.getDegreeAndMajor() + " - " + education.getGPA() +
                " GPA\n" + education.getSchoolName() + " - " + education.getLocation(), paragraph);

        String skillsList = (String) skill.getSkills().get(0);
        if (skill.getSkills().size() > 1) {
            for (int i = 1; i < skill.getSkills().size(); i++) {
                skillsList = skillsList + ", " + (String) skill.getSkills().get(i);
            }
        }

        Chunk skillDataChunk = new Chunk(skillsList, paragraph);
        Chunk workDataChunk = new Chunk(work.getTitle() + " - " + work.getDates() + "\n" + work.getCompany() + " - " + work.getLocation() + "\n" + work.getDescription(), paragraph);
        Chunk customDataChunk = new Chunk(custom.getBody(), paragraph);

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

        Paragraph customHead = new Paragraph(customHeader);
        Paragraph customDataPara = new Paragraph(customDataChunk);

        document.add(eduHeader);
        document.add(eduData);
        document.add(spacing);
        document.add(skillHead);
        document.add(skillDataPara);
        document.add(spacing);
        document.add(workHead);
        document.add(workDataPara);
        document.add(spacing);
        document.add(customHead);
        document.add(customDataPara);
        document.add(spacing);


        document.close();
    }

}

