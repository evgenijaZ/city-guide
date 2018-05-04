package edu.kpi.jee.cityguide.resources;


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
public class CategoryResource {
    private
    CategoryRepository repository;

    @Autowired
    public CategoryResource(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public List<Category> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/get")
    public Category getById(@RequestParam(value = "id") int id) {
        return repository.getOne(id);
    }

    @GetMapping(value = "/getPlaces")
    public Set<Place> getPlaces(@RequestParam(value = "id") int id){
        return repository.getOne(id).getPlaces();
    }
//    @PostMapping(value = "/load")
//    public List<Category> persist(@RequestBody final Category category) {
//        repository.save(Category);
//        return repository.findAll();
//    }
}
