package edu.kpi.jee.cityguide.services;

import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.repositories.CategoryRepository;
import edu.kpi.jee.cityguide.repositories.CityRepository;
import edu.kpi.jee.cityguide.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private final
    PlaceRepository repository;
    private final
    CityRepository cityRepository;

    @Autowired
    public PlaceService(PlaceRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    public List<Place> getAll() {
        return repository.findAll();
    }

    public Place getById(int id) {
        return repository.getOne(id);
    }

    public List<Place> search(Integer id, String name){
        List<Place> places = null;
        if(id!=null && id==0) id=null;
        if (name!=null && name.isEmpty()) name=null;
        if(id==null&&name==null) return repository.findAll();
        if(id == null) return repository.findAllByNameContains(name);
        if(name == null) return repository.findAllByCity(cityRepository.getOne(id));
        return repository.findAllByNameContainsAndCity(name, cityRepository.getOne(id));
    }

    public Place create(Place place) {
      return repository.saveAndFlush(place);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
        repository.flush();
    }

    public void update(Place place) {
        repository.saveAndFlush(place);
    }
}
