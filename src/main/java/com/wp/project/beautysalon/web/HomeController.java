package com.wp.project.beautysalon.web;

import com.wp.project.beautysalon.model.Appointment;
import com.wp.project.beautysalon.service.AppointmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.*;


@Controller
public class HomeController {

    private final AppointmentService appointmentService;
    private final TemplateEngine templateEngine;
    private final ServletContext servletContext;


    public HomeController(AppointmentService appointmentService, TemplateEngine templateEngine, ServletContext servletContext) {
        this.appointmentService = appointmentService;
        this.templateEngine = templateEngine;
        this.servletContext = servletContext;
    }

    @GetMapping({"/home", "/"})
    public String homePage(){

        return "layout.html";
    }


    @GetMapping({"/about"})
    public String about_us(){

        return "about_us.html";
    }

//    @GetMapping(value = "/pdf")
//    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
//
////        Reservation reservation = reservationService.findReservationById(id);
//        Appointment appointment = appointmentService.findbyId(id);
//
//        WebContext context = new WebContext(request, response, servletContext);
//       // context.setVariable("reservation", reservation);
//        context.setVariable("appointment", appointment);
//        String orderHtml = templateEngine.process("pdf", context);
//
////        ByteArrayOutputStream target = new ByteArrayOutputStream();
////        InputStream inputStream = new FileInputStream("src/main/resources/templates/pdf.html");
////        HtmlConverter.convertToPdf(inputStream, target);
//
//        ByteArrayOutputStream target = new ByteArrayOutputStream();
//        ConverterProperties converterProperties = new ConverterProperties();
//        converterProperties.setBaseUri("http://localhost:8080/");
//        /* Call convert method */
//        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);
//
//        byte[] bytes = target.toByteArray();
//        /* Send the response as downloadable PDF */
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(bytes);
//    }


}
