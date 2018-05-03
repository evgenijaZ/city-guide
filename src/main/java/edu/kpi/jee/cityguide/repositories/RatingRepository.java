package edu.kpi.jee.cityguide.repositories;


import edu.kpi.jee.cityguide.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
