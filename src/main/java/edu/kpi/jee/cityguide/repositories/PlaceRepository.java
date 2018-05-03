package edu.kpi.jee.cityguide.repositories;


import edu.kpi.jee.cityguide.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
}
