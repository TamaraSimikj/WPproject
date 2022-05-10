package com.wp.project.beautysalon.web;

import com.wp.project.beautysalon.model.Termin;
import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.service.TerminiService;
import com.wp.project.beautysalon.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TerminiController {

    private final TerminiService terminiService;
    private final UserService userService;

    public TerminiController(TerminiService terminiService, UserService userService) {
        this.terminiService = terminiService;
        this.userService = userService;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping( "/termini")
    public String listTermini(Model model) {
        List<Termin> termini = this.terminiService.listAllNotReserved();
        // da se stavat listanje na nerezervirani termini

        model.addAttribute("termini", termini);


        return "free_termini.html";

    }
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    @GetMapping("/termini/add")
    public String showAdd(Model model) {
        List<User> vraboteni = this.userService.listEmployees();
        model.addAttribute("vraboteni",vraboteni);
        return "termini_form.html";
    }
    @GetMapping("/termini/{id}/edit")
    public String showEdit(@PathVariable Integer id, Model model) {
        Termin termin = this.terminiService.findbyId(id);
        List<User> vraboteni = this.userService.listEmployees();

        model.addAttribute("termin",termin);
        model.addAttribute("vraboteni",vraboteni);

        return "termini_form.html";
    }
    @PostMapping("/termini")
    public String create(@RequestParam String startTime,
                         @RequestParam  Integer duration,
                         @RequestParam  String employeeId) {


        this.terminiService.create(LocalDateTime.parse(startTime),duration,employeeId);

        return "redirect:/termini";
    }
    @PostMapping("/termini/{id}")
    public String update(@PathVariable Integer id, //terminId
                         @RequestParam  String startTime,
                         @RequestParam Integer duration,
                         @RequestParam String employeeId) {
        this.terminiService.update(id,LocalDateTime.parse(startTime),duration,employeeId);
        return "redirect:/termini";
    }
    @PostMapping({"/termini/{id}/delete"})
    public String delete(@PathVariable Integer id) {
        this.terminiService.delete(id);
        return "redirect:/termini";
    }

}
