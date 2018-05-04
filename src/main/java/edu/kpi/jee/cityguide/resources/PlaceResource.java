package edu.kpi.jee.cityguide.resources;


import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("place")
public class PlaceResource {
    private final
    PlaceRepository repository;

    @Autowired
    public PlaceResource(PlaceRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public List<Place> getAll(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "/get")
    public Place getById(@RequestParam(value = "id") int id) {
        return repository.getOne(id);
    }
}
