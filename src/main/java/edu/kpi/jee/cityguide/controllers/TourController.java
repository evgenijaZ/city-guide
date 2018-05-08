package edu.kpi.jee.cityguide.controllers;

import edu.kpi.jee.cityguide.entities.Tour;
import edu.kpi.jee.cityguide.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tour")
public class TourController {
    private final TourService service;

    @Autowired
    public TourController(TourService service) {
        this.service = service;
    }


    @GetMapping(value = "/all")
    public List<Tour> getAll() {
        return service.getAll();
    }

    @GetMapping
    public Tour getById(@RequestParam(value = "id") int id) {
        return service.getById(id);
    }

}
