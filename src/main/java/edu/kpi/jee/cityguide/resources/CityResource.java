package edu.kpi.jee.cityguide.resources;

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
public class CityResource {
    private final
    CityRepository repository;

    @Autowired
    public CityResource(CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public List<City> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/get")
    public City getById(@RequestParam(value = "id") int id) {
        return repository.getOne(id);
    }

//    @PostMapping(value = "/load")
//    public List<City> persist(@RequestBody final City city) {
//        repository.save(City);
//        return repository.findAll();
//    }
}
