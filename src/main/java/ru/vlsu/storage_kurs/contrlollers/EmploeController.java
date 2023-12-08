package ru.vlsu.storage_kurs.contrlollers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmploeController {
    @GetMapping("/home")
    public String loginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Получение имени пользователя
        String username = authentication.getName();

        System.out.println(username);
        return "home";
    }
}
