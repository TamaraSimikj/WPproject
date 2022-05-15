package com.wp.project.beautysalon.web;

import com.lowagie.text.DocumentException;
import com.wp.project.beautysalon.AppPDFExporter;
import com.wp.project.beautysalon.model.Role;
import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.model.exceptions.InvalidArgumentException;
import com.wp.project.beautysalon.model.exceptions.PasswordNotMatchException;
import com.wp.project.beautysalon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String hasError,
                               @RequestParam(required = false) String error, Model model) {
        model.addAttribute("hasError", hasError);
        model.addAttribute("error", error);
        return "login.html";
    }

    @GetMapping({"/register", "/registerEmployee"})
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Role> roles = Arrays.asList(Role.values());
        model.addAttribute("roles", roles);
        return "register.html";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String email,
                           @RequestParam String phoneNumber) {
        try {
            userService.register(username, password, repeatedPassword, name, surname, Role.ROLE_CLIENT, email, phoneNumber);
            return "redirect:/login";
        } catch (InvalidArgumentException | PasswordNotMatchException ex) {
            return "redirect:/register?error=" + ex.getMessage();
        }
    }

    @PostMapping("/registerEmployee")
    public String registerEmployee(
            @RequestParam Role role,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String repeatedPassword,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email,
            @RequestParam String phoneNumber) {
        try {
            userService.register(username, password, repeatedPassword, name, surname, role, email, phoneNumber);
            return "redirect:/home"; // da redirektira negde so employees lista
        } catch (InvalidArgumentException | PasswordNotMatchException ex) {
            return "redirect:/register?error=" + ex.getMessage();
        }
    }

}
