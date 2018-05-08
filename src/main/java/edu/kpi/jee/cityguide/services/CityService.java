package edu.kpi.jee.cityguide.services;

import edu.kpi.jee.cityguide.entities.City;
import edu.kpi.jee.cityguide.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository repository;

    public List<City> getAll() {
        return repository.findAll();
    }
}
