package resu.resumaker.services;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.json.*;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

@Controller
public class SubmitController {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "program";
    static final String PASS = "1234";

    @PostMapping("/submit")
    public void submit(@RequestParam String contactPOST, @RequestParam String educationPOST,
                       @RequestParam String skillPOST, @RequestParam String workPOST) throws DocumentException, FileNotFoundException {

        System.out.println(contactPOST);
        System.out.println(educationPOST);
        System.out.println(skillPOST);
        System.out.println(workPOST);

        JSONArray contact_json = new JSONArray(contactPOST);
        JSONArray education_json = new JSONArray(educationPOST);
        JSONArray skill_json = new JSONArray(skillPOST);
        JSONArray work_json = new JSONArray(workPOST);

        System.out.println(contact_json.join(" "));
        System.out.println(education_json.join(" "));
        System.out.println(skill_json.join(" "));
        System.out.println(work_json.join(" "));

        contactSubmit(contact_json);
        educationSubmit(education_json);
        skillSubmit(skill_json);
        workSubmit(work_json);

        PDFService generate = new PDFService();
    }

    public static void contactSubmit(JSONArray contactPOST) {

        String values = "('"+ contactPOST.getString(0) + " " + contactPOST.getString(1) + "', '" + contactPOST.getString(2) + "', '" + contactPOST.getString(3) + "')";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ) {
            String sql = "INSERT INTO public.\"Contact\"(name, email, phone) VALUES" + values + ";";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void educationSubmit(JSONArray educationPOST) {
        for(int i = 0; i < educationPOST.length(); i++){
            String values = "('" + educationPOST.getJSONArray(i).getString(0) + "', '" + educationPOST.getJSONArray(i).getString(1) + "', '" + educationPOST.getJSONArray(i).getString(2) + "', '" + educationPOST.getJSONArray(i).getString(3) + "', '" + educationPOST.getJSONArray(i).getString(4) + "')";
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
            ) {
                String sql = "INSERT INTO public.\"Education\"(school, degree, gpa, location, dates) VALUES" + values + ";";
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void skillSubmit(JSONArray skillPOST) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            for (int i = 0; i < skillPOST.length(); i++) {
                String sql = "INSERT INTO public.\"Skills\"(skills) VALUES ('" + skillPOST.getString(i) + "');";
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 1 2 3 4
    public static void workSubmit(JSONArray workPOST) {
        for (int i = 0; i < workPOST.length(); i++) {
            String values = "('" + workPOST.getJSONArray(i).getString(0) + "', '" + workPOST.getJSONArray(i).getString(1) + "', '" + workPOST.getJSONArray(i).getString(2) + "', '" + workPOST.getJSONArray(i).getString(3) + "', '" + workPOST.getJSONArray(i).getString(4) + "')";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();
            ) {
                String sql = "INSERT INTO public.\"Work\"(title, company, dates, location, description) VALUES" + values + ";";
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
