package me.devsign.home.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @PostMapping(path = "/api/test/return/object")
    public Optional<? extends Object> returnObject() {
        return Optional.empty();
    }
}
