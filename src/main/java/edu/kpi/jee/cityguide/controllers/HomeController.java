package edu.kpi.jee.cityguide.controllers;

import edu.kpi.jee.cityguide.entities.User;
import edu.kpi.jee.cityguide.repositories.CityRepository;
import edu.kpi.jee.cityguide.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final CityRepository cityRepository;

    @Autowired
    public HomeController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = {"","/", "/home", "/index"})
    public ModelAndView init(HttpSession session) {
        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityRepository.findAll());
        }
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        if (user != null)
//            session.setAttribute("user", user.getName());
        return new ModelAndView("home");
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
