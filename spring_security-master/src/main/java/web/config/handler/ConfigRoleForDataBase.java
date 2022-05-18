package web.config.handler;

import org.springframework.stereotype.Component;
import web.model.Role;
import web.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class ConfigRoleForDataBase {

    private final UserService userService;

    public ConfigRoleForDataBase(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {
        userService.saveRole(new Role(1L, "ROLE_ADMIN"));
        userService.saveRole(new Role(2L, "ROLE_USER"));
        userService.createFistUserAdminWhenStartApplication();
    }
}
