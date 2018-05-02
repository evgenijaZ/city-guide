package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tour")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tour {
    @Id
    @Column(name = "t_id")
    private int id;
    @Column(name = "t_name")
    private String name;
    @Column(name = "t_description")
    private String description;
    @ManyToMany(mappedBy = "tours")
    @JsonBackReference
    private Set<Place> places;

    public Tour() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}
