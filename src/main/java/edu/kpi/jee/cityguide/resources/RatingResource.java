package edu.kpi.jee.cityguide.resources;

import edu.kpi.jee.cityguide.entities.Rating;
import edu.kpi.jee.cityguide.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rating")
public class RatingResource {
    private final
    RatingRepository repository;

    @Autowired
    public RatingResource(RatingRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public List<Rating> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/get")
    public Rating getById(@RequestParam(value = "id") int id) {
        return repository.getOne(id);
    }

//    @PostMapping(value = "/load")
//    public List<Rating> persist(@RequestBody final Rating rating) {
//        repository.save(Rating);
//        return repository.findAll();
//    }
}