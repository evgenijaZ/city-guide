package edu.kpi.jee.cityguide.controllers;


import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.services.CategoryService;
import edu.kpi.jee.cityguide.services.CityService;
import edu.kpi.jee.cityguide.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("place")
public class PlaceController {
    private final PlaceService service;
    private final CityService cityService;
    private final CategoryService categoryService;


    @Autowired
    public PlaceController(PlaceService service, CityService cityService,
                           CategoryService categoryService) {
        this.service = service;
        this.cityService = cityService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/all")
    public ModelAndView getAll(HttpSession session,
                               ModelAndView model) {
        if (!model.hasView()) model.setViewName("home");
        model.addObject("placeList", service.getAll());
        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityService.getAll());
        }
        return model;
    }

    @GetMapping
    public ModelAndView getById(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("place");
        modelAndView.addObject("place", service.getById(id));
        return modelAndView;
    }


    @GetMapping(value = "/search")
    public ModelAndView getById(@RequestParam(value = "city", required = false) Integer id,
                                @RequestParam(value = "name", required = false) String name,
                                ModelAndView modelAndView
    ) {
        if (!modelAndView.hasView()) modelAndView.setViewName("home");
        List<Place> places
                = service.search(id, name);
        if (places != null)
            modelAndView.addObject("placeList", places);
        return modelAndView;
    }

    @GetMapping(value = "/new")
    public ModelAndView newPlace(HttpSession session,
                                 ModelAndView modelAndView) {
        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityService.getAll());
        }
        if (session.getAttribute("categoryList") == null) {
            session.setAttribute("categoryList", categoryService.getAll());
        }
        modelAndView.addObject("place", new Place());
        modelAndView.setViewName("new-place");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createPlace(@ModelAttribute Place place,
                                    RedirectAttributes redirectAttributes) {
        place = service.create(place);
        redirectAttributes.addAttribute("id", place.getId());
        return new ModelAndView("place");
    }

    @DeleteMapping
    public String deletePlace(@RequestParam int id) {
        service.deleteById(id);
        return "home";
    }

    @PostMapping(value = "/delete")
    public ModelAndView postDeletePlace(@RequestParam int id) {
        service.deleteById(id);
        return new ModelAndView("home");
    }

    @PutMapping
    public ModelAndView updatePlace(@ModelAttribute Place place,
                                    RedirectAttributes redirectAttributes) {
        if (place != null) {
            service.update(place);
            redirectAttributes.addAttribute("id", place.getId());
        }
        return new ModelAndView("place");
    }

    @GetMapping("/edit")
    public ModelAndView editPlace(@ModelAttribute("place") Place place,
                                  @RequestParam int id,
                                  HttpSession session) {
        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityService.getAll());
        }
        if (session.getAttribute("categoryList") == null) {
            session.setAttribute("categoryList", categoryService.getAll());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("place", service.getById(id));
        modelAndView.setViewName("edit-place");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView postUpdatePlace(@ModelAttribute("place") Place place,
                                        RedirectAttributes redirectAttributes) {
        if (place != null) {
            service.update(place);
            redirectAttributes.addAttribute("id", place.getId());
        }
        return new ModelAndView("place");
    }
}
