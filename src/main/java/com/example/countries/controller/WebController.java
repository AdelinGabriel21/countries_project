package com.example.countries.controller;

import com.example.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public String index(Model model) {
        // This passes the list of countries to the HTML page
        model.addAttribute("countries", countryService.getAllCountries());
        return "index"; // Looks for templates/index.html
    }
}