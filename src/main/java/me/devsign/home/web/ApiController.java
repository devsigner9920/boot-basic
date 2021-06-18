package me.devsign.home.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @PostMapping(path = "/api/{token}/test/select")
    public void tryController() {
        
    }
}
