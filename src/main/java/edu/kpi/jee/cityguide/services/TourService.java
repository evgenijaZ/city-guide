package edu.kpi.jee.cityguide.services;

import edu.kpi.jee.cityguide.entities.Tour;
import edu.kpi.jee.cityguide.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {
    private final TourRepository repository;

    @Autowired
    public TourService(TourRepository repository) {
        this.repository = repository;
    }

    public List<Tour> getAll() {
        return repository.findAll();
    }

    public Tour getById(int id) {
        return repository.getOne(id);
    }
}
