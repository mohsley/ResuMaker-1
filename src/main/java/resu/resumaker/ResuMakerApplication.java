package resu.resumaker;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import userData.ContactData;
import userData.EducationData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

@SpringBootApplication

public class ResuMakerApplication {

    public static void main(String[] args) throws IOException, DocumentException, URISyntaxException {
        // look into checking what the user inputs to see if it's valid (i.e. capitalization) (try/catch and error messages)
        // look @ spring-web when making UI
        SpringApplication.run(ResuMakerApplication.class, args);
        ContactData contact = new ContactData();
        contact.setName("Mohny Tonhamad");
        contact.setEmail("mohamad@tony.kevin.au.com");
        contact.setPhoneNumber("000-000-0000");

        EducationData education = new EducationData();
        education.setSchoolName("University of California, Santa Cruz");
        education.setDegreeAndMajor("Bachelor of Science: Computer Science");
        education.setGPA("4.0");
        education.setLocation("Santa Cruz, CA");
        education.setDates("2032-09 - 2034-06");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("sample.pdf"));

        document.open();

        Font title = FontFactory.getFont(FontFactory.HELVETICA, 24, BaseColor.BLACK);
        Font subtitle = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
        Font header = FontFactory.getFont(FontFactory.HELVETICA, 20, BaseColor.BLACK);
        Font paragraph = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        Chunk nameData = new Chunk(contact.getName(), title);
        Chunk contactData = new Chunk(contact.getEmail() + " | " + contact.getPhoneNumber(), subtitle);
        Chunk newLine = new Chunk("\n",paragraph);
        Chunk educationHeader = new Chunk("Education", header);
        Chunk skillsHeader = new Chunk("Skills", header);
        Chunk workHeader = new Chunk("Work Experience", header);
        Chunk customHeader = new Chunk("User Defined", header);
        Chunk educationData = new Chunk( education.getDegreeAndMajor() + " - " + education.getGPA() +
                " GPA\n" + education.getSchoolName() + " - " + education.getLocation());

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
        Paragraph workHead = new Paragraph(workHeader);
        Paragraph customHead = new Paragraph(customHeader);

        document.add(eduHeader);
        document.add(eduData);
        document.add(spacing);
        document.add(skillHead);
        document.add(spacing);
        document.add(workHead);
        document.add(spacing);
        document.add(customHead);
        document.add(spacing);

        document.close();
    }

}

