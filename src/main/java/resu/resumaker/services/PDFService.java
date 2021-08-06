package resu.resumaker.services;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import resu.resumaker.userData.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;

public class PDFService {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "program";
    static final String PASS = "1234";
    static final String CONTACT_QUERY = "SELECT name, email, phone FROM public.\"Contact\"";
    static final String EDUCATION_QUERY = "SELECT school, degree, gpa, location, dates FROM public.\"Education\"";
    static final String WORK_QUERY = "SELECT * FROM public.\"Work\"";
    static final String SKILLS_QUERY = "SELECT * FROM public.\"Skills\"";
    static ContactData contactDB() {
        ContactData query = new ContactData();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(CONTACT_QUERY);) {
            rs.next();
            query = new ContactData(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
    static ArrayList<EducationData> educationDB() {
        ArrayList<EducationData> query = new ArrayList<EducationData>();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(EDUCATION_QUERY);) {
            while (rs.next()) {
                EducationData query_row = new EducationData(rs.getString("school"), rs.getString("degree"), rs.getString("gpa"), rs.getString("location"), rs.getString("dates"));
                query.add(query_row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
    static ArrayList<WorkData> workDB() {
        ArrayList<WorkData> query = new ArrayList<WorkData>();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(WORK_QUERY);) {
            while (rs.next()) {
                WorkData query_row = new WorkData(rs.getString("title"), rs.getString("company"), rs.getString("dates"), rs.getString("location"), rs.getString("description"));
                query.add(query_row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
    static ArrayList<SkillsData> skillsDB() {
        ArrayList<SkillsData> query = new ArrayList<SkillsData>();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SKILLS_QUERY);) {
            while (rs.next()) {
                SkillsData query_row = new SkillsData(rs.getString("skills"));
                query.add(query_row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
    public PDFService() throws FileNotFoundException, DocumentException, AddressException, NoSuchProviderException {
        Document document = new Document();
        String localDir = System.getProperty("user.dir");
        File file = new File(localDir + "\\resume.pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        Font title = FontFactory.getFont(FontFactory.HELVETICA, 24, BaseColor.BLACK);
        Font subtitle = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
        Font header = FontFactory.getFont(FontFactory.HELVETICA, 20, BaseColor.BLACK);
        Font paragraph = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
        Font miniSpace = FontFactory.getFont(FontFactory.HELVETICA, 6, BaseColor.BLACK);

        Chunk newLine = new Chunk("\n",paragraph);
        Paragraph spacing = new Paragraph(newLine);

        Chunk miniNewLine = new Chunk("\n", miniSpace);
        Paragraph miniSpacing = new Paragraph(miniNewLine);

        ContactData contact = contactDB();

        Chunk nameData = new Chunk(contact.getName(), title);
        Paragraph name = new Paragraph(nameData);
            name.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(name);

        Chunk contactData = new Chunk(contact.getEmail() + " | " + contact.getPhone(), subtitle);
        Paragraph contactInfo = new Paragraph(contactData);
            contactInfo.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(contactInfo);
            document.add(spacing);
        ArrayList<EducationData> education = educationDB();

        Chunk educationHeader = new Chunk("Education", header);
        Paragraph eduHeader = new Paragraph(educationHeader);
            document.add(eduHeader);

        for (int i = 0; i < education.size(); i++) {
            Chunk educationData = new Chunk( education.get(i).getDegree() + " - " + education.get(i).getGpa() +
                    " GPA\n" + education.get(i).getSchool() + " - " + education.get(i).getLocation() +"\nDates Attended: " + education.get(i).getDates(), paragraph);
            Paragraph eduData = new Paragraph(educationData);
            document.add(eduData);

            document.add(miniSpacing);
        }
        document.add(miniSpacing);

        ArrayList<SkillsData> skill = skillsDB();

        Chunk skillsHeader = new Chunk("Skills", header);
        Paragraph skillHead = new Paragraph(skillsHeader);
            document.add(skillHead);

        String skillsList = (String) skill.get(0).getSkills();
            if (skill.size() > 1) {
            for (int i = 1; i < skill.size(); i++) {
                skillsList = skillsList + ", " + (String) skill.get(i).getSkills();
            }
        }

        Chunk skillDataChunk = new Chunk(skillsList, paragraph);
        Paragraph skillDataPara = new Paragraph(skillDataChunk);

            document.add(skillDataPara);
            document.add(spacing);

        ArrayList<WorkData> work = workDB();

        Chunk workHeader = new Chunk("Work Experience", header);
        Paragraph workHead = new Paragraph(workHeader);
            document.add(workHead);

            for (int i = 0; i < work.size(); i++) {
            Chunk workDataChunk = new Chunk(work.get(i).getTitle() + " - " + work.get(i).getDates() + "\n" + work.get(i).getCompany() + " - " + work.get(i).getLocation() + "\n" + work.get(i).getDescription(), paragraph);
            Paragraph workDataPara = new Paragraph(workDataChunk);
            document.add(workDataPara);

            document.add(miniSpacing);
        }

            document.add(miniSpacing);
            document.close();
        }
}
