package resu.resumaker.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class SubmitController {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "program";
    static final String PASS = "1234";

    @PostMapping("/submit")
    public void submit(@RequestParam String[] contactPOST, @RequestParam String[] skillPOST,
                       @RequestParam String[] workPOST, @RequestParam String[] educationPOST) {
        contactSubmit(contactPOST);
        skillSubmit(skillPOST);
        workSubmit(workPOST);
        educationSubmit(educationPOST);
    }

    public static void contactSubmit(String[] contactPOST) {
        String values = "('"+ contactPOST[0] + "', '" + contactPOST[1] + "', '" + contactPOST[2] + "')";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ) {
            String sql = "INSERT INTO public.\"Contact\"(name, email, phone) VALUES" + values + ";";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void skillSubmit(String[] skillPOST) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            for (int i = 0; i < skillPOST.length; i++) {
                String sql = "INSERT INTO public.\"Skills\"(skills) VALUES ('" + skillPOST[i] + "');";
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void workSubmit(String[] workPOST) {
        String values = "('"+ workPOST[0] + "', '" + workPOST[1] + "', '" + workPOST[2] + "', '" + workPOST[3] + "', '" + workPOST[4] +"')";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "INSERT INTO public.\"Work\"(title, company, dates, location, description) VALUES" + values + ";";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void educationSubmit(String[] educationPOST) {
        String values = "('"+ educationPOST[0] + "', '" + educationPOST[1] + "', '" + educationPOST[2] + "', '" + educationPOST[3] + "', '" + educationPOST[4] +"')";
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
