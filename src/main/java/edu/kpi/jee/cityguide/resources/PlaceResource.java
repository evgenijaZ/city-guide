package edu.kpi.jee.cityguide.resources;


import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.repositories.CityRepository;
import edu.kpi.jee.cityguide.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.List;


@RestController
@RequestMapping("place")
public class PlaceResource {
    private final
    PlaceRepository repository;
    private final
    CityRepository cityRepository;

    @Autowired
    public PlaceResource(PlaceRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = "/all")
    public ModelAndView getAll(HttpSession session, ModelAndView model) {
        if (!model.hasView()) model.setViewName("home");
        model.addObject("placeList", repository.findAll());
        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityRepository.findAll());
        }
        return model;
    }

    @GetMapping(value = "/get")
    public ModelAndView getById(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("place");
        modelAndView.addObject("place", repository.getOne(id));
        return modelAndView;
    }


    @GetMapping(value = "/search")
    public ModelAndView getById(@RequestParam(value = "city", required = false) Integer id, @RequestParam(value = "name", required = false) String name, ModelAndView modelAndView) {
        if (!modelAndView.hasView()) modelAndView.setViewName("home");
        List<Place> places = null;
        if (id != null && id != 0 && name != null)
            places = repository.findAllByNameContainsAndCity(name, cityRepository.getOne(id));
        else if (name != null && !name.isEmpty())
            places = repository.findAllByNameContains(name);
        else if (id != null && id != 0) places = repository.findAllByCity(cityRepository.getOne(id));
        if (places != null)
            modelAndView.addObject("placeList", places);
        return modelAndView;
    }
}
