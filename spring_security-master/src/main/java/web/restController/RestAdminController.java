package web.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.dto.UserDto;
import web.mapper.UserMapper;
import web.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/")
@RestController
public class RestAdminController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    List<UserDto> all() {
        return userService.getListUsers().stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @PostMapping("users")
    void createUser(@RequestBody UserDto newUser) {
        newUser.setId(null);
        userService.saveUser(UserMapper.INSTANCE.toEntity(newUser));
    }

    @GetMapping("users/{id}")
    UserDto getUserById(@PathVariable Long id) {
        return UserMapper.INSTANCE.toDTO(userService.getUserById(id));
    }

    @PutMapping("users/{id}")
    void updateUserById(@RequestBody UserDto newEmployee, @PathVariable Long id) {
        UserDto userDto = UserMapper.INSTANCE.toDTO(userService.getUserById(id));
        userDto.setName(newEmployee.getName());
        userDto.setRoles(newEmployee.getRoles());
        userService.saveUser(UserMapper.INSTANCE.toEntity(userDto));
    }

    @DeleteMapping("users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
