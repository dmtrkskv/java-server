package myapp.main.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import myapp.main.dto.UserDto;
import myapp.main.models.User;

@Component
public class UserMapper {
    ModelMapper modelMapper = new ModelMapper();

    UserMapper() {
        modelMapper.addMappings(new PropertyMap<User, UserDto>() {
            protected void configure() {
                // todo: обход выпиливания поля "password", выпиливать нужно,
                // но разобраться, почему это происходит автоматически
                map().setPassword(source.getPassword());
            }
        });
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        modelMapper.map(user, userDto);

        return userDto;
    }

    public List<UserDto> toDto(List<User> users) {
        List<UserDto> usersDto = new ArrayList<UserDto>(users.size());

        users.forEach(user -> { 
            UserDto userDto = new UserDto();

            modelMapper.map(user, userDto);

            usersDto.add(userDto);
        });      

        return usersDto;
    }

    public User toModel(UserDto userDto) {
        User user = new User();
        
        modelMapper.map(userDto, user);

        return user;
    }
}