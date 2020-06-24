package myapp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myapp.main.services.UserService;
import myapp.main.dto.UserDto;
import myapp.main.models.User;
import myapp.main.mappers.UserMapper;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/createUser")
    public String createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toModel(userDto);

        userService.create(user);

        return "user created";
    }

    @GetMapping("/getUser")
    public UserDto getUser(@RequestParam(value = "id", required = true) int id) {
        User user = userService.findById(id);

        return userMapper.toDto(user);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getUsersList")
    public List<UserDto> getUsersList() {
        return userMapper.toDto(userService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.principal.username == #userDto.getUsername()")
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.toModel(userDto);

        userService.update(user);

        return "user updated";
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