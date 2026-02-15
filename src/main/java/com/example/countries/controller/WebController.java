package com.example.countries.controller;

import com.example.countries.model.Country;
import com.example.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public String index(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id != null) {
            // Search Mode: Fetch only one country and put it in a list
            Country country = countryService.getCountryById(id);
            List<Country> countries = (country != null) ? List.of(country) : Collections.emptyList();
            model.addAttribute("countries", countries);
        } else {
            // Default Mode: Fetch all countries
            model.addAttribute("countries", countryService.getAllCountries());
        }
        return "index"; // Renders templates/index.html
    }
}