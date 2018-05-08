package edu.kpi.jee.cityguide.controllers;

import edu.kpi.jee.cityguide.entities.City;
import edu.kpi.jee.cityguide.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {
    private final
    CityRepository repository;

    @Autowired
    public CityController(CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public List<City> getAll() {
        return repository.findAll();
    }

    @GetMapping
    public City getById(@RequestParam(value = "id") int id) {
        return repository.getOne(id);
    }
}
