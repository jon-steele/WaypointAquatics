package com.waypointaquatics.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.waypointaquatics.web.mail.EmailService;

@Controller
public class HomeController {

    private final EmailService emailService;

    @Autowired
    public HomeController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @PostMapping("/request")
    public String submitForm(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             @RequestParam("date") String date,
                             @RequestParam("starttime") String startTime,
                             @RequestParam("endtime") String endTime,
                             @RequestParam("location") String location,
                             @RequestParam("size") int partySize,
                             @RequestParam("comments") String comments) {

        // Compose the email content
        String subject = "New Reservation Request";
        String message = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Phone Number: " + phone + "\n" +
                "Proposed Date: " + date + "\n" +
                "Proposed Start Time: " + startTime + "\n" +
                "Proposed End Time: " + endTime + "\n" +
                "Location: " + location + "\n" +
                "Party Size: " + partySize + "\n" +
                "Comments: " + comments;

        // Send email
        emailService.sendEmail("waypointaquaticsdev@gmail.com", subject, message);

        return "redirect:/success"; 
    }
}
