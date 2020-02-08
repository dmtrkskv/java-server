package myapp.main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myapp.main.services.UserService;
import myapp.main.models.User;

@RestController
public class UserController {
    private final UserService userService = new UserService();

    @GetMapping("/getUser")
    public User getUser(@RequestParam(value = "id", required = true) int id) {
        return userService.findById(id);       
    }

    @GetMapping("/createUser")
    public String createUser(@RequestParam(value = "name", required = true) String name) {
        User user = new User(name);
        userService.create(user);

        return "success";     
    }
}