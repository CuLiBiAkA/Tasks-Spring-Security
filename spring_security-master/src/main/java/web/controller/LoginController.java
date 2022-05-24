package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static web.config.MyConstantString.*;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping(value = "login")
    public String loginPage() {
        return LOGIN;
    }
}