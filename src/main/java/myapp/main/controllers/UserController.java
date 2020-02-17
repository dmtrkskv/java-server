package myapp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myapp.main.services.UserService;
import myapp.main.models.User;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public String createUser(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {
        User user = new User(username, password);
        userService.create(user);

        return "user created";
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam(value = "id", required = true) int id) {
        return userService.findById(id);
    }

    @GetMapping("/getUsersList")
    public List<User> getUsersList() {
        return userService.findAll();
    }

    @GetMapping("/updateUser")
    public String updateUser(@RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "username", required = false) String username,
             @RequestParam(value = "password", required = false) String password) {
        User user = userService.findById(id);

        if (user != null) {
            if (username != null) {
                user.setUsername(username);
            }

            if (password != null) {
                user.setPassword(password);
            }

            userService.update(user);

            return "user updated";
        } else {
            return "denied";
        }
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id", required = true) int id) {
        User user = userService.findById(id);

        if (user != null) {
            userService.delete(user);

            return "user deleted";
        } else {
            return "denied";
        }
    }
}