package web.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConfigRootForDataBase {

    @Autowired
    private UserService userService;

    @PostConstruct
    private void postConstruct() {
        userService.saveRole(new Role(1L, "ROLE_ADMIN"));
        userService.saveRole(new Role(2L, "ROLE_USER"));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, "ROLE_ADMIN"));
        User user = new User(1L, "ADMIN", "ADMIN", 17, "CMOSHA", roleSet);
        user.setPassword(userService.getPasswordCoder(user.getPassword()));
        userService.saveUser(user);
    }
}
