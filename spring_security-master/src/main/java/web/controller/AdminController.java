package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {


    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        boolean flagUserRole = false;
        model.addAttribute("flag", flagUserRole);
        return "newUser";
    }

    @PostMapping("/")
    public String createUser(@ModelAttribute("user") User user, boolean flag) {
        var sameNameUserInDB = userService.getListUsers().stream().anyMatch(user1 -> user1.getUsername().equals(user.getUsername()));
        if (!sameNameUserInDB) {
            userService.setUserRole(user, flag);
            user.setPassword(userService.getPasswordEncoder(user.getPassword()));
            userService.saveUser(user);
            return "redirect:/admin";
        } else {
            return "redirect:/admin/new";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping()
    public String getListUsers(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("role", user.getRoles().stream().findFirst().get().getName());
        return "getUser";
    }

    @GetMapping("/{id}/editUser")
    public String editUser(Model model, @PathVariable("id") Long id) {
        boolean flag = false;
        model.addAttribute("flag", flag);
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping("/{id}")
    public String updateUserById(@ModelAttribute("user") User user, boolean flag) {
        userService.setUserRole(user, flag);
        user.setPassword(userService.getPasswordEncoder(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
    }
}