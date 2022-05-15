package com.wp.project.beautysalon.web;

import com.lowagie.text.DocumentException;
import com.wp.project.beautysalon.AppPDFExporter;
import com.wp.project.beautysalon.model.Appointment;
import com.wp.project.beautysalon.model.SalonService;
import com.wp.project.beautysalon.model.Termin;
import com.wp.project.beautysalon.service.AppointmentService;
import com.wp.project.beautysalon.service.SalonServiceService;
import com.wp.project.beautysalon.service.TerminiService;
import com.wp.project.beautysalon.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.Date;
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
    @PreAuthorize("isAuthenticated()")
    public String showListAppointments(Model model) {

        List<Appointment> appointmentList = this.appointmentService.listAll();

        model.addAttribute("appointments", appointmentList);

        return "appointmentsList.html";
    }

    @GetMapping("/makeAppointment")
    @PreAuthorize("isAuthenticated()")
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
                         @RequestParam Integer terminId
                         // @RequestParam(required = false) Integer uplataId
    ) {
        List<SalonService> services = this.salonService.findAllById(serviceIds);
        //     User client = this.userService.findById(clientName);

        Appointment appointment = this.appointmentService.create(clientName, terminId, services);

        return "redirect:/appointments";
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/appointments/{id}/edit")
    public String showEdit(@PathVariable Integer id, Model model) {
        Appointment appointment = this.appointmentService.findbyId(id);
        List<Termin> termini = this.terminiService.listAllNotReserved();
        List<SalonService> services = this.salonService.findAll();

        model.addAttribute("appointment", appointment);
        model.addAttribute("termini", termini);
        model.addAttribute("services", services);

        return "editAppointment.html";
    }

    @PostMapping("/appointments/{id}")
    public String update(@PathVariable Integer id,
                         @RequestParam List<String> serviceIds,
                         @RequestParam String clientName,
                         @RequestParam Integer terminId) {

        List<SalonService> services = this.salonService.findAllById(serviceIds);
        this.appointmentService.update(id, clientName, terminId, services);

        return "redirect:/appointments";
    }


    @PostMapping({"/appointments/{id}/delete"})
    public String delete(@PathVariable Integer id) {

        this.appointmentService.delete(id);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/{id}/exportpdf")
    public void exportToPDF(@PathVariable Integer id, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=appointment" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        Appointment appointment = this.appointmentService.findbyId(id);

        AppPDFExporter exporter = new AppPDFExporter(appointment);
        exporter.export(response);

    }
}
