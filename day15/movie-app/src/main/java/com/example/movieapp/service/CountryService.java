package com.example.movieapp.service;


import com.example.movieapp.entity.Country;
import com.example.movieapp.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Integer id) {
        if (countryRepository.findById(id).isPresent()) {
            return countryRepository.findById(id).get();
        }
        return null;
    }
}