package edu.kpi.jee.cityguide.controllers;


import edu.kpi.jee.cityguide.entities.Category;
import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.services.CategoryService;
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
    private final
    CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public List<Category> getAll() {
        return service.getAll();
    }

    @GetMapping
    public Category getById(@RequestParam(value = "id") int id) {
        return service.getById(id);
    }

    @GetMapping(value = "/places")
    public Set<Place> getPlaces(@RequestParam(value = "id") int id) {
        return service.getPlaces(id);
    }
}
