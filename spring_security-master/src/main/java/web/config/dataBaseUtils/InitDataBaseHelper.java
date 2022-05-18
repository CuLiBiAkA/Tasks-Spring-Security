package web.config.dataBaseUtils;

import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitDataBaseHelper {

    private final UserService userService;

    public InitDataBaseHelper(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {
        userService.saveRole(new Role(1L, "ROLE_ADMIN"));
        userService.saveRole(new Role(2L, "ROLE_USER"));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, "ROLE_ADMIN"));
        User user = new User(1L, "ADMIN", "ADMIN", 9, "CMOSHA", roleSet);
        user.setPassword(userService.getPasswordEncoder(user.getPassword()));
        userService.saveUser(user);
    }
}

