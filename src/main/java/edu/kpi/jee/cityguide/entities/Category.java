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
}
