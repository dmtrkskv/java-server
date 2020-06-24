package myapp.main.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeResourceController {
    @GetMapping("/getCommonResource")
    public String getCommonResource() {
        return "success";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getClosedResource")
    public String getClosedResource() {
        return "success";
    }
}