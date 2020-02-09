package myapp.main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myapp.main.services.UserService;
import myapp.main.models.User;
import java.util.List;

@RestController
public class UserController {
    
    private final UserService userService = new UserService();    

    @GetMapping("/createUser")
    public String createUser(@RequestParam(value = "name", required = true) String name) {
        User user = new User(name);
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
    @RequestParam(value = "name", required = true) String name) {
        User user = userService.findById(id);

        if (user != null) {
            user.setName(name);
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