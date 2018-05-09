package edu.kpi.jee.cityguide.repositories;

import edu.kpi.jee.cityguide.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
