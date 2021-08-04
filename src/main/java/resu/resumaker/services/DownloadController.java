package resu.resumaker.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class DownloadController {
    @GetMapping("/download")
    public InputStreamResource download(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename =\"Resume.pdf\"");

        String localDir = System.getProperty("user.dir");
        localDir = (localDir + "\\resume.pdf");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(localDir));
        return resource;
    }
}
