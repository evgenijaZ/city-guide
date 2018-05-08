package edu.kpi.jee.cityguide.controllers;


import edu.kpi.jee.cityguide.entities.Category;
import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("category")
public class CategoryController {
    private
    CategoryRepository repository;

    @Autowired
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public List<Category> getAll() {
        return repository.findAll();
    }

    @GetMapping
    public Category getById(@RequestParam(value = "id") int id) {
        return repository.getOne(id);
    }

    @GetMapping(value = "/places")
    public Set<Place> getPlaces(@RequestParam(value = "id") int id) {
        return repository.getOne(id).getPlaces();
    }
}
