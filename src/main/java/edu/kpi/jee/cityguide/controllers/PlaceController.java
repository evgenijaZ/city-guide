package edu.kpi.jee.cityguide.controllers;


import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.repositories.CategoryRepository;
import edu.kpi.jee.cityguide.repositories.CityRepository;
import edu.kpi.jee.cityguide.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("place")
public class PlaceController {
    private final
    PlaceRepository repository;
    private final
    CityRepository cityRepository;
    private
    final CategoryRepository categoryRepository;

    @Autowired
    public PlaceController(PlaceRepository repository, CityRepository cityRepository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
        this.categoryRepository = categoryRepository;
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

    @GetMapping
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

    @GetMapping(value = "/new")
    public ModelAndView newPlace(HttpSession session,
                                 ModelAndView modelAndView) {
        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityRepository.findAll());
        }
        if (session.getAttribute("categoryList") == null) {
            session.setAttribute("categoryList", categoryRepository.findAll());
        }
        modelAndView.addObject("place", new Place());
        modelAndView.setViewName("new-place");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createPlace(@ModelAttribute Place place,
                                    RedirectAttributes redirectAttributes) {
        place = repository.save(place);
        repository.flush();
        redirectAttributes.addAttribute("id", place.getId());
        return new ModelAndView("place");
    }

    @DeleteMapping
    public ModelAndView deletePlace(@RequestParam int id) {
        repository.deleteById(id);
        return new ModelAndView("home");
    }

    @PostMapping(value = "/delete")
    public ModelAndView postDeletePlace(@RequestParam int id) {
        repository.deleteById(id);
        repository.flush();
        return new ModelAndView("redirect:home");
    }

    @PutMapping
    public ModelAndView updatePlace(@ModelAttribute Place place,
                                    RedirectAttributes redirectAttributes) {
        if (place != null) {
            repository.save(place);
            redirectAttributes.addAttribute("id", place.getId());
        }
        return new ModelAndView("place");
    }

    @GetMapping("/edit")
    public ModelAndView editPlace(@ModelAttribute("place") Place place,
                                  @RequestParam int id,
                                  HttpSession session) {

        if (session.getAttribute("cityList") == null) {
            session.setAttribute("cityList", cityRepository.findAll());
        }
        if (session.getAttribute("categoryList") == null) {
            session.setAttribute("categoryList", categoryRepository.findAll());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("place", repository.getOne(id));
        modelAndView.setViewName("edit-place");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView postUpdatePlace(@ModelAttribute("place") Place place,
                                        RedirectAttributes redirectAttributes) {
        if (place != null) {
            repository.saveAndFlush(place);
            redirectAttributes.addAttribute("id", place.getId());
        }
        return new ModelAndView("place");
    }
}
