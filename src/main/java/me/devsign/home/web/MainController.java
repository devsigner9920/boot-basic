package me.devsign.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        System.out.println("TEST");
        return "index";
    }
}