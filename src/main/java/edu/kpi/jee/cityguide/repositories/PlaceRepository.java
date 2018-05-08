package edu.kpi.jee.cityguide.repositories;


import edu.kpi.jee.cityguide.entities.City;
import edu.kpi.jee.cityguide.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findAllByNameContainsAndCity(String name, City city);

    List<Place> findAllByNameContains(String name);

    List<Place> findAllByCity(City city);

}
