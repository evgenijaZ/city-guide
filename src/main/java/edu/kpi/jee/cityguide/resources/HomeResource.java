package edu.kpi.jee.cityguide.resources;

import edu.kpi.jee.cityguide.entities.City;
import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.repositories.CityRepository;
import edu.kpi.jee.cityguide.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeResource {
    private final PlaceRepository placeRepository;
    private final CityRepository cityRepository;

    @Autowired
    public HomeResource(PlaceRepository placeRepository, CityRepository cityRepository) {
        this.placeRepository = placeRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = {"", "/", "/home", "/index"})
    public String init(HttpSession session) {
        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityRepository.findAll());
        }
        return "home";
    }

    @GetMapping(value = "/search")
    public String searchSubmit(@RequestParam(value = "city") int id,
                               @RequestParam(value = "name") String name,
                               ModelAndView model) {
        if (id == 0 && name.isEmpty()) return "redirect:place/all";
        if (id != 0 && name.isEmpty()) return "redirect:place/search?city=" + id;
        if (id != 0) return "redirect:place/search?name=" + name + "&city=" + id;
        return "redirect:place/search?name=" + name;
    }
}
