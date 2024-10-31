package net.javaguides.thymeleaf.controller;

import net.javaguides.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("variable-expression")
    public String variableExpression(Model model) {
        User user = new User(
                "William",
                "william@email.com",
                "ADMIN",
                "Male"
        );
        model.addAttribute("user", user);
        return "variable-expression";
    }

    @GetMapping("/selection-expression")
    public String selectionExpression(Model model) {
        User user = new User(
                "William",
                "william@email.com",
                "ADMIN",
                "Male"
        );
        model.addAttribute("user", user);
        return "variable-expression";
    }

    @GetMapping("/message-expression")
    public String messageExpression(Model model) {
//        User user = new User(
//                "William",
//                "william@email.com",
//                "ADMIN",
//                "Male"
//        );
//        model.addAttribute("user", user);
        return "message-expression";
    }
}
