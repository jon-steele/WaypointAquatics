package com.waypointaquatics.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.waypointaquatics.web.services.EmailService;
// import com.waypointaquatics.web.services.RecaptchaService;

@Controller
public class HomeController {

    @Value("${recaptcha_secret_key}")
    private String recaptchaSecretKey;
    @Value("${recaptcha_site_key}")
    private String recaptchaSiteKey;
    @Value("${recaptcha_url}")
    private String recaptchaUrl;

    private final EmailService emailService;
    // private final RecaptchaService recaptchaService;

    @Autowired
    public HomeController(EmailService emailService) {
        this.emailService = emailService;
        // this.recaptchaService = recaptchaService;
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
                             @RequestParam("g-recaptcha-response") String recaptchaResponse,
                             @RequestParam("comments") String comments) {
        
        // Verify that the Recapcha was completed
        // if (!recaptchaService.verifyRecaptcha(recaptchaResponse)) {
        //     return "redirect:/";
        // }

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
