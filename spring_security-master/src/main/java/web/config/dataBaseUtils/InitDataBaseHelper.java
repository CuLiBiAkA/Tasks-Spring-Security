package web.config.dataBaseUtils;

import org.springframework.stereotype.Component;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitDataBaseHelper {

    private final UserDao userDao;
    private final UserService userService;

    public InitDataBaseHelper(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    final static String roleAdmin = "ROLE_ADMIN";
    final static String roleUser = "ROLE_USER";

    @PostConstruct
    private void postConstruct() {
        userService.saveRole(new Role(1L, roleAdmin));
        userService.saveRole(new Role(2L, roleUser));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, roleAdmin));
        User user = new User(1L, "ADMIN", "ADMIN", 9, "CMOSHA", roleSet);
        user.setPassword(userService.getPasswordEncoder(user.getPassword()));
        userService.saveUser(user);
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(new Role(2L, roleUser));
        User user2 = new User(2L, "USER", "USER", 4, "TOGE", roleSet2);
        user2.setPassword(userService.getPasswordEncoder(user.getPassword()));
        userService.saveUser(user2);
    }
    @PreDestroy
    private void cleanDBBase(){
       userDao.deleteAll();
    }
}