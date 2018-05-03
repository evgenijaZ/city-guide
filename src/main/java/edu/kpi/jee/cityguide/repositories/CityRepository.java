package edu.kpi.jee.cityguide.repositories;


import edu.kpi.jee.cityguide.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
