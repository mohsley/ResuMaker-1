package resu.resumaker.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
    @GetMapping("/personal_info")
    public String form() {
        return "personal_info";
    }
}
