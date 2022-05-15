package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getListUsers();

    void saveUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User findUserByName(String name);

    void setUserRoot(User user, boolean flag);

    String getPasswordCoder(String password);

    void saveRole(Role role);
}
