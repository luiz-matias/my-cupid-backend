package com.luizmatias.findadev.controllers;

import com.luizmatias.findadev.dtos.GreetingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class IndexController {

    @GetMapping("/")
    GreetingResponse index() {
        return new GreetingResponse("Hello world!");
    }

}
