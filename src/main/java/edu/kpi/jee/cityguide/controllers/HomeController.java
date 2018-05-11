package edu.kpi.jee.cityguide.controllers;

import edu.kpi.jee.cityguide.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final CityRepository cityRepository;
    @Autowired
    public HomeController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = {"", "/", "/home", "/index"})
    public View init(HttpSession session) {
        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityRepository.findAll());
        }
        return new InternalResourceView("/place/all");
    }

    @GetMapping(value = "/search")
    public String searchSubmit(@RequestParam(value = "city") int id,
                               @RequestParam(value = "name") String name) {
        if (id == 0 && name.isEmpty()) return "redirect:place/all";
        if (id != 0 && name.isEmpty()) return "redirect:place/search?city=" + id;
        if (id != 0) return "redirect:place/search?name=" + name + "&city=" + id;
        return "redirect:place/search?name=" + name;
    }
}
