package com.wp.project.beautysalon.web;

import com.wp.project.beautysalon.model.Appointment;
import com.wp.project.beautysalon.model.SalonService;
import com.wp.project.beautysalon.model.Termin;
import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.service.AppointmentService;
import com.wp.project.beautysalon.service.SalonServiceService;
import com.wp.project.beautysalon.service.TerminiService;
import com.wp.project.beautysalon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final SalonServiceService salonService;
    private final TerminiService terminiService;
    private final UserService userService;


    public AppointmentController(AppointmentService appointmentService, SalonServiceService salonService, TerminiService terminiService, UserService userService) {
        this.appointmentService = appointmentService;
        this.salonService = salonService;
        this.terminiService = terminiService;

        this.userService = userService;
    }


    @GetMapping("/appointments")
    public String showListAppointments(Model model) {

        List<Appointment> appointmentList = this.appointmentService.listAll();

        model.addAttribute("appointments", appointmentList);

        return "appointmentsList.html";
    }

    @GetMapping( "/makeAppointment")
    public String MakeAppointment(Model model) {

        List<Termin> termini = this.terminiService.listAllNotReserved();
        List<SalonService> services = this.salonService.findAll();


        model.addAttribute("termini", termini);
        model.addAttribute("services", services);

        return "appointment-form.html";
    }
    @PostMapping("/makeAppointment")
    public String create(@RequestParam List<String> serviceIds,
                         @RequestParam String clientName,
                         @RequestParam  Integer terminId
                         // @RequestParam(required = false) Integer uplataId
    ) {
        List<SalonService> services = this.salonService.findAllById(serviceIds);
   //     User client = this.userService.findById(clientName);

       Appointment appointment = this.appointmentService.create(clientName,terminId,services);

        return "redirect:/appointments";
    }

// da se dodadat delete i edit , i da se printaat uslugite vo edna kolona
}
