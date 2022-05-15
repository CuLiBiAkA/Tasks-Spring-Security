package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/new")
    public String newUser(Model model, User user) {
        boolean flag = false;
        model.addAttribute("user", user);
        model.addAttribute("flag", flag);
        return "newUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user, boolean flag) {
        userService.setUserRoot(user, flag);
        user.setPassword(userService.getPasswordCoder(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin")
    public String getListUsers(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "admin";
    }

    @GetMapping("/admin/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "getUser";
    }

    @GetMapping("/admin/{id}/editUser")
    public String editUser(Model model, @PathVariable("id") Long id) {
        boolean flag = false;
        model.addAttribute("flag", flag);
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping("/admin/{id}")
    public String updateUserById(@ModelAttribute("user") User user, boolean flag) {
        userService.setUserRoot(user, flag);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}