package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
    @Id
    @Column(name = "c_id")
    private int id;
    @Column(name = "c_name")
    private String name;
    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private Set<Place> places;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Set<Place> places) {
        this.name = name;
        this.places = places;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, Set<Place> places) {
        this.id = id;
        this.name = name;
        this.places = places;
    }

    public Category() {
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id == category.id && (name != null ? name.equals(category.name) : category.name == null) && places.equals(category.places);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0) + (places != null ? places.hashCode() : 0);
        return result;
    }
}
