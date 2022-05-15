package com.wp.project.beautysalon.web;

import com.wp.project.beautysalon.model.Rate;
import com.wp.project.beautysalon.model.SalonService;
import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.service.RateService;
import com.wp.project.beautysalon.service.SalonServiceService;
import com.wp.project.beautysalon.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RateController {
    private final SalonServiceService salonService;
    private final UserService userService;
    private final RateService rateService;

    public RateController(SalonServiceService salonService, UserService userService, RateService rateService) {
        this.salonService = salonService;
        this.userService = userService;
        this.rateService = rateService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/services/rate")
    public String showRate(@AuthenticationPrincipal User principal, Model model) {
        List<SalonService> services = this.salonService.findAll();
        model.addAttribute("services", services);
        model.addAttribute("loggedInUser", principal);

        return "rate.html";
    }

    @PostMapping("/services/rate")
    public String rate(@RequestParam String serviceId,
                       @RequestParam Integer rateValue,
                       @RequestParam(required = false) String comment,
                       @RequestParam(required = false) String client) {
        SalonService service = this.salonService.findbyId(serviceId);
        User user = this.userService.findById(client);

        this.salonService.rate(service, rateValue, comment, user);
        return "redirect:/servicesRates";
    }

    @GetMapping("/servicesRates")
    public String listOfRates(Model model) {
        List<Rate> rates = this.rateService.listAll();
        model.addAttribute("rates", rates);
        return "listRates.html";
    }

    @PostMapping({"/servicesRates/{id}/delete"})
    public String deleteRate(@PathVariable Long id) {

        this.rateService.delete(id);
        return "redirect:/servicesRates";
    }

}
