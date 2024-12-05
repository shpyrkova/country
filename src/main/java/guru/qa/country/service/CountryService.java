package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll().stream().map(Country::fromEntity).toList();
    }

    public Country addCountry(Country country) {
        CountryEntity ce = countryRepository.save(CountryEntity.fromCountry(country));
        return Country.fromEntity(ce);
    }

    public Country updateName(Country country) {
        CountryEntity ce = countryRepository.findByCountryCode(country.countryCode());
        if (ce == null) {
            throw new IllegalArgumentException("Country with code " + country.countryCode() + " not found");
        }
        ce.setCountryName(country.countryName());
        ce = countryRepository.save(ce);
        return Country.fromEntity(ce);
    }

}
