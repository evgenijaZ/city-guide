package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@ToString(exclude = "places")
@EqualsAndHashCode(exclude = "places")
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
}
