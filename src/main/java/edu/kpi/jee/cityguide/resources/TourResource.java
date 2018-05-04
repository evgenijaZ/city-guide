package edu.kpi.jee.cityguide.resources;

import edu.kpi.jee.cityguide.entities.Tour;
import edu.kpi.jee.cityguide.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tour")
public class TourResource {
    private final
    TourRepository repository;

    @Autowired
    public TourResource(TourRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public List<Tour> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/get")
    public Tour getById(@RequestParam(value = "id") int id) {
        return repository.getOne(id);
    }

//    @PostMapping(value = "/load")
//    public List<Tour> persist(@RequestBody final Tour tour) {
//        repository.save(Tour);
//        return repository.findAll();
//    }
}
