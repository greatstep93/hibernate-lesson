package com.example.hibernatelesson.controllers;

import com.example.hibernatelesson.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @PostMapping("/create")
    public void createCity(@RequestParam String name) {
        cityService.saveNewCity(name);
    }
}
