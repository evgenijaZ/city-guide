package edu.kpi.jee.cityguide.resources;

import edu.kpi.jee.cityguide.entities.User;
import edu.kpi.jee.cityguide.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserResource {
    private final
    UserRepository repository;

    @Autowired
    public UserResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/all")
    public List<User> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/get")
    public User getById(@RequestParam(value = "id") int id) {
        return repository.getOne(id);
    }

//    @PostMapping(value = "/load")
//    public List<User> persist(@RequestBody final User user) {
//        repository.save(user);
//        return repository.findAll();
//    }
}
