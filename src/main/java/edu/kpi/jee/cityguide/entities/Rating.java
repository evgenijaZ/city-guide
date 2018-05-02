package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rating {
    @Id
    @Column(name = "r_id")
    private int id;
    @Column(name = "r_value")
    private float value;
    @ManyToOne
    @JoinColumn(name = "r_user_id", nullable = false)
    @JsonBackReference
    private User user;
    @ManyToOne
    @JoinColumn(name = "r_place_id", nullable = false)
    @JsonBackReference
    private Place place;


    public Rating(User user, Place place, float value) {
        this.user = user;
        this.place = place;
        this.value = value;
    }

    public Rating() {
    }

    public User getUser() {
        return user;
    }

    public Place getPlace() {
        return place;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        return id == rating.id && !(value != rating.value);
    }
}
