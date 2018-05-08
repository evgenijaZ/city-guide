package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@ToString(exclude = "places")
@EqualsAndHashCode(exclude = "places")
@Table(name = "city")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City {
    @Id
    @Column(name = "c_id")
    private int id;
    @Column(name = "c_name")
    private String name;
    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private Set<Place> places;
}
