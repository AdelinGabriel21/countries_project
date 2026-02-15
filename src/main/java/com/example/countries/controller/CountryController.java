package com.example.countries.controller;

import com.example.countries.model.Country;
import com.example.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    // Requirement: GET all countries
    @GetMapping
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    // Requirement: GET country with city by ID
    // Because of @JsonManagedReference, this will include the city list.
    @GetMapping("/{id}")
    public Country getById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    // Requirement: Update a country
    @PutMapping("/{id}")
    public Country update(@PathVariable Long id, @RequestBody Country country) {
        return countryService.updateCountry(id, country);
    }

    // Requirement: Remove a country with a given ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        countryService.deleteCountry(id);
    }
}