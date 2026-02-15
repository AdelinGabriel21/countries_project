package com.example.countries.util;
import com.example.countries.model.City;
import com.example.countries.model.Country;
import com.example.countries.repository.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
@Component
public class DataInitializer implements CommandLineRunner {
    private final CountryRepository countryRepository;
    public DataInitializer(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    @Override
    public void run(String @NonNull ... args) {
        if (countryRepository.count() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Map<String, Object>>> typeReference = new TypeReference<>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/countries.json");
            try {
                List<Map<String, Object>> countriesData = mapper.readValue(inputStream, typeReference);
                for (Map<String, Object> data : countriesData) {
                    Country country = new Country();
                    country.setName((String) data.get("country"));
                    // Optional: country.setPopulation((Integer) data.get("population"));
                    City capital = new City();
                    capital.setName((String) data.get("capital"));
                    capital.setCapital(true);
                    capital.setCountry(country);
                    country.getCities().add(capital);
                    countryRepository.save(country);
                }
                System.out.println("--- Database Seeded Successfully! ---");
            } catch (Exception e) {
                System.out.println("Unable to save countries: " + e.getMessage());
            }
        }
    }
}