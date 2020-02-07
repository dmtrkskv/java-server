package myapp.main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myapp.main.services.UserService;
import myapp.main.models.User;

@RestController
public class UserController {

    @GetMapping("/getUser")
    public User getUserInfo(@RequestParam(value = "id", required = false, defaultValue = "1") int id) {
        UserService userService = new UserService();
        return userService.findUser(id);       
    }
}