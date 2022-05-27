package web.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.User;
import web.service.UserService;

import static web.config.MyConstantString.USER;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user1")
    public User showUser() {
        return userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
