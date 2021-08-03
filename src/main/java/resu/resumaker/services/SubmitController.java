package resu.resumaker.services;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public void submit(@RequestParam String[] contactPOST, @RequestParam String[][] educationPOST,
                       @RequestParam String[] skillPOST, @RequestParam String[][] workPOST) throws DocumentException, FileNotFoundException {
        contactSubmit(contactPOST);
        educationSubmit(educationPOST);
        skillSubmit(skillPOST);
        workSubmit(workPOST);
        PDFService generate = new PDFService();
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
    public static void educationSubmit(String[][] educationPOST) {
        System.out.println(Arrays.deepToString(educationPOST));
//        for(int i = 0; i < educationPOST.length; i++){
//            String values = "('" + educationPOST[i][0] + "', '" + educationPOST[i][0] + "', '" + educationPOST[i][0] + "', '" + educationPOST[i][0] + "', '" + educationPOST[i][0] + "')";
//            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                Statement stmt = conn.createStatement();
//            ) {
//                String sql = "INSERT INTO public.\"Education\"(school, degree, gpa, location, dates) VALUES" + values + ";";
//                stmt.execute(sql);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
    public static void skillSubmit(String[] skillPOST) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            for (String s : skillPOST) {
                String sql = "INSERT INTO public.\"Skills\"(skills) VALUES ('" + s + "');";
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 1 2 3 4
    public static void workSubmit(String[][] workPOST) {
        for (String[] strings : workPOST) {
            String values = "('" + strings[0] + "', '" + strings[1] + "', '" + strings[2] + "', '" + strings[3] + "', '" + strings[4] + "')";
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
