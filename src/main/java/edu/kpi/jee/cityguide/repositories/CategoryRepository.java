package edu.kpi.jee.cityguide.repositories;


import edu.kpi.jee.cityguide.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
