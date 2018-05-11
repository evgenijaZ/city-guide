package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@ToString(exclude = {"city", "categories", "tours"})
@EqualsAndHashCode(exclude = {"city", "categories", "tours"})
@Table(name = "place")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int id;
    @Column(name = "p_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "p_city", nullable = false)
    @JsonBackReference
    private City city;
    @Column(name = "p_address")
    private String address;
    @Column(name = "p_description")
    private String description;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonManagedReference
    @JoinTable(
            name = "m2m_place_category",
            joinColumns = {@JoinColumn(name = "place_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> categories;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonManagedReference
    @JoinTable(
            name = "m2m_place_tour",
            joinColumns = {@JoinColumn(name = "place_id")},
            inverseJoinColumns = {@JoinColumn(name = "tour_id")}
    )
    private Set<Tour> tours;

    public boolean hasCategory(Category category) {
        return categories.contains(category);
    }
}
