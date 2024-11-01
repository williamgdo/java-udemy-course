package net.javaguides.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
    // handle method to handle /helloworld request
    // http://localhost:8080/hello-world
    @GetMapping("/hello-world")
    public String helloWorld(Model model) {
        model.addAttribute(
                "message",
                "Hello World!"
        );
        return "hello-world"; // name of template in src/main/resources/templates folder
    }
}
