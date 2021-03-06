package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;
import java.util.List;
import static web.config.MyConstantString.ADMIN_LOW;
import static web.config.MyConstantString.EDIT_USER;
import static web.config.MyConstantString.FLAG;
import static web.config.MyConstantString.GET_USER;
import static web.config.MyConstantString.NEW_USER;
import static web.config.MyConstantString.REDIRECT;
import static web.config.MyConstantString.URL_ADMIN;
import static web.config.MyConstantString.USER;
import static web.config.MyConstantString.USERS;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        boolean flagSetRoleUser = false;
        model.addAttribute(FLAG, flagSetRoleUser);
        return NEW_USER;
    }

    @PostMapping("/")
    public String createUser(@ModelAttribute("user") User user, BindingResult resolver, @ModelAttribute("flag") boolean flagSetRoleUser) {
        if (resolver.hasErrors()) {
            List<ObjectError> errorList = resolver.getAllErrors();
            for (ObjectError temp : errorList)
                System.out.println(temp);
            return NEW_USER;
        }
        userService.setUserRole(user, flagSetRoleUser);
        user.setPassword(userService.getPasswordEncoder(user.getPassword()));
        userService.saveUser(user);
        return REDIRECT + URL_ADMIN;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return REDIRECT + URL_ADMIN;
    }

    @GetMapping()
    public String getListUsers(Model model) {
        model.addAttribute(USERS, userService.getListUsers());
        return ADMIN_LOW;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute(USER, user);
        return GET_USER;
    }

    @GetMapping("/{id}/editUser")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        boolean flagSetRoleUser = ADMIN_LOW.equals(user.getUserRoles());
        model.addAttribute(FLAG, flagSetRoleUser);
        model.addAttribute(USER, user);
        return EDIT_USER;
    }

    @PostMapping("/{id}")
    public String updateUserById(@ModelAttribute("user") User user, @ModelAttribute("flag") boolean flagSetRoleUser) {
        userService.setUserRole(user, flagSetRoleUser);
        user.setPassword(userService.getPasswordEncoder(user.getPassword()));
        userService.saveUser(user);
        return REDIRECT + URL_ADMIN;
    }
}