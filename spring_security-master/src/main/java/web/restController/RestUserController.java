package web.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.dto.UserDto;
import web.mapper.UserMapper;
import web.service.UserService;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user1")
    public UserDto showUser() {
        return UserMapper.INSTANCE.toDTO(userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}
