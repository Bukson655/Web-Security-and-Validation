package pl.sb.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login-page";
    }

}