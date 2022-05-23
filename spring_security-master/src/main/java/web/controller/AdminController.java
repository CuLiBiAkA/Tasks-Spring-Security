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

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        boolean flagSetRoleUser = false;
        model.addAttribute("flag", flagSetRoleUser);
        return "newUser";
    }

    @PostMapping("/")
    public String createUser(@ModelAttribute("user") User user, BindingResult resolver, @ModelAttribute("flag") boolean flagSetRoleUser) {
       if(resolver.hasErrors()){
           List<ObjectError> errorList = resolver.getAllErrors();
            for(ObjectError temp: errorList)
                System.out.println(temp);
           return "newUser";
       }
        userService.setUserRole(user, flagSetRoleUser);
        user.setPassword(userService.getPasswordEncoder(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
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
        return "getUser";
    }

    @GetMapping("/{id}/editUser")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        boolean flagSetRoleUser =user.getUserRoles().equals("admin");;
        model.addAttribute("flag", flagSetRoleUser);
        model.addAttribute("user",user);
        return "editUser";
    }

    @PostMapping("/{id}")
    public String updateUserById(@ModelAttribute("user") User user,@ModelAttribute("flag") boolean flagSetRoleUser) {
        userService.setUserRole(user, flagSetRoleUser);
        user.setPassword(userService.getPasswordEncoder(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
    }
}