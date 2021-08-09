package resu.resumaker;

import com.itextpdf.text.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;


@SpringBootApplication
public class ResuMakerApplication{

    public static void main(String[] args) throws IOException, DocumentException, URISyntaxException, SQLException {
        SpringApplication.run(ResuMakerApplication.class, args);
    }
}

