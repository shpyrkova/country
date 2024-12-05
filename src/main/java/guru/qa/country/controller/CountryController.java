package guru.qa.country.controller;

import guru.qa.country.model.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/")
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    @PostMapping("/country/add")
    public Country add(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PatchMapping("/country/name")
    public Country updateName(@RequestBody Country country) {
        return countryService.updateName(country);
    }

}
