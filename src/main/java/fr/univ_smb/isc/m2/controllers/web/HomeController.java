package fr.univ_smb.isc.m2.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login", method = GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(Principal principal) {
        System.out.println("HomeController, User logged : " + principal.getName());
        return "hello-protected";
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/resources/html/index.html";
    }


}
