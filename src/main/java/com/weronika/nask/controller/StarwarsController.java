package com.weronika.nask.controller;

import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.service.StarwarsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarwarsController {
    private final StarwarsService starwarsService;

    public StarwarsController(StarwarsService starwarsService) {
        this.starwarsService = starwarsService;
    }

    @GetMapping("/characters/{id}")
    public StarwarsCharacter getCharacter(@PathVariable int id){
        return starwarsService.getCharacter(id);
    }
}
