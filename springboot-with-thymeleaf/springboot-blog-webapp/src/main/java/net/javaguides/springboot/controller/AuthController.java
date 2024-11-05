package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.dto.RegistrationDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;
//    private final RoleService roleService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String showRegistrationForm(
            Model model
    ) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "/auth/register";
    }

    @PostMapping("/register/save")
    public String saveRegistration(
            Model model,
            @Valid @ModelAttribute("user") RegistrationDto user,
            BindingResult result
    ) {
        User existingUser = userService.findByEmail(user.getEmail());

        if (existingUser != null) {
            result.rejectValue(
                    "email",
                    null,
                    "There is already a user with same email."
            );
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/auth/register";
        }

        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
}
