package myapp.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/getUser")
    public User getUserInfo(@RequestParam(value = "firstName", required = false, defaultValue = "Iri") String firstName,
            @RequestParam(value = "secondName", required = false, defaultValue = "Sergeeva") String secondName,
            @RequestParam(value = "age", required = false, defaultValue = "20") int age) {
        return new User(firstName, secondName, age);
    }
}