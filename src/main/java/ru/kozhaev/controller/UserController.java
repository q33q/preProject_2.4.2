package ru.kozhaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kozhaev.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping()
    public String userPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getByName(principal.getName()));
        return "users/show";
    }

}
