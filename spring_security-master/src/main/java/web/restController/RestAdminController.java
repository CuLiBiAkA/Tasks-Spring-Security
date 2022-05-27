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
import web.model.User;
import web.service.UserService;

import java.util.List;

@RequestMapping("/admin1")
@RestController
public class RestAdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> all() {
        return userService.getListUsers();
    }

    @PostMapping("/users/new")
    void createUser(@RequestBody User newUser) {
        userService.saveUser(newUser);
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    void updateUserById(@RequestBody User newEmployee, @PathVariable Long id) {
        User user = userService.getUserById(id);
        user.setName(newEmployee.getName());
        user.setRoles(newEmployee.getRoles());
        userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
