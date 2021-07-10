package resu.resumaker;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

@SpringBootApplication

public class ResuMakerApplication {

    public static void main(String[] args) throws IOException, DocumentException, URISyntaxException {
        SpringApplication.run(ResuMakerApplication.class, args);
        UserData payload = new UserData();
        payload.setName("Tony Mahamad");
        payload.setJob("Software Engineer");
        payload.setEmail("mohamad@tony.kevin.au.com");
        payload.setPhoneNumber("000-000-0000");

        System.out.println(payload.getName());
        System.out.println(payload.getJob());
        System.out.println(payload.getEmail());
        System.out.println(payload.getPhoneNumber());
    }

    private static void iTextTest() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));

        document.open();
        // header for now.
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World, soon I will be a resume.", font);
        chunk.setCharacterSpacing(10);
        document.add(chunk);

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table);
        document.add(table);
        document.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPadding(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table) {
        table.addCell("r1, c1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }
}

