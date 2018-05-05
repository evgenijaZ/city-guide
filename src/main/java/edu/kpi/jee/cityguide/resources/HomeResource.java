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

import javax.servlet.http.HttpSession;

@Controller
public class HomeResource {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CityRepository cityRepository;

    @GetMapping(value={"","/","/home","/index"})
    public String searchForm(HttpSession session, Model model) {
        if (!model.containsAttribute("place")) {
            Place place = new Place();
            place.setCity(new City());
            model.addAttribute("place", place);
        }
        if (session.getAttribute("cityList")==null) {
            session.setAttribute("cityList", cityRepository.findAll());
        }
        return "home";
    }

    @GetMapping(value = "/search")
    public String searchSubmit(@RequestParam(value = "city", required = true) int id,
                               @RequestParam(value = "name", required = true) String name,
                               Model model) {
        if (id == 0 && name.isEmpty()) return "redirect:place/all";
        return "home";
    }
}
