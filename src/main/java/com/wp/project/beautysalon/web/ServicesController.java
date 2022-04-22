package com.wp.project.beautysalon.web;

import com.wp.project.beautysalon.model.SalonService;
import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.model.exceptions.ServiceIdReservedException;
import com.wp.project.beautysalon.service.SalonServiceService;
import com.wp.project.beautysalon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ServicesController {

    private final SalonServiceService salonServiceService;
    private final UserService userService;



    public ServicesController(SalonServiceService salonServiceService, UserService userService ) {
        this.salonServiceService = salonServiceService;
        this.userService = userService;

    }


    @GetMapping("/services")
    public String showServices( Model model) {
        List<SalonService> salonServices = this.salonServiceService.findAll();
        model.addAttribute("services", salonServices);
        return "services.html";
    }

    @GetMapping("/services/add")
    public String showAdd(@RequestParam(required = false) String error,
                          Model model) {
         List<User> employees = this.userService.listEmployees();
       // List<Employee> employees = this.employeeService.listAll();
        model.addAttribute("employees",employees);
        model.addAttribute("hasError",error!=null);
        model.addAttribute("error", error);

        return "createService.html";
    }

    @GetMapping("/services/{id}/edit")
    public String showEdit(@PathVariable String id, Model model) {
        SalonService service = this.salonServiceService.findbyId(id);
       List<User> employees = this.userService.listEmployees();
        //List<Employee> employees = this.employeeService.listAll();
        model.addAttribute("service",service);
        model.addAttribute("employees",employees);

        return "services_form.html";
    }

    @PostMapping("/services")
    public String create(@RequestParam String serviceId,
                         @RequestParam  String serviceName,
                         @RequestParam  Integer price,
                         @RequestParam List<String> employeeIds) {

        try{
            this.salonServiceService.create(serviceId, serviceName, price, employeeIds);
            return "redirect:/services";
        }
        catch (ServiceIdReservedException exception)
        {
            return "redirect:/services/add?error="+exception.getMessage();
        }

    }

    @PostMapping("/services/{id}")
    public String update(@PathVariable String id,
                         @RequestParam String serviceName,
                         @RequestParam Integer price,
                         @RequestParam(required = false) List<String> employeeIds) {

        this.salonServiceService.update(id, serviceName, price, employeeIds);

        return "redirect:/services";
    }
    @PostMapping({"/services/{id}/delete"})
    public String delete(@PathVariable String id) {

        this.salonServiceService.delete(id);
        return "redirect:/services";
    }


}
