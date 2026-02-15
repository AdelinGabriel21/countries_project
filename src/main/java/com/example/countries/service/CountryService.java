package com.example.countries.service;
import com.example.countries.model.Country;
import com.example.countries.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
    public Country updateCountry(Long id, Country updatedCountry) {
        return countryRepository.findById(id)
                .map(country -> {
                    country.setName(updatedCountry.getName());
                    country.setPopulation(updatedCountry.getPopulation());
                    return countryRepository.save(country);
                }).orElse(null);
    }
}