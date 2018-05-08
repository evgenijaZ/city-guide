package edu.kpi.jee.cityguide.services;

import edu.kpi.jee.cityguide.entities.Category;
import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    private
    CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(int id) {
        return repository.getOne(id);
    }

    public Set<Place> getPlaces(int categoryId) {
        return repository.getOne(categoryId).getPlaces();
    }
}
