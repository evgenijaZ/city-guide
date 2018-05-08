package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Column(name = "u_id")
    private int id;
    @Column(name = "u_username")
    private String userName;
    @Column(name = "u_email")
    private String email;
    @Column(name = "u_password")
    private String password;
//    @OneToMany(mappedBy = "user")
//    @JsonManagedReference
//    private Set<Rating> ratings;

//    public void ratePlace(Place place, float value) {
//        Rating rating = new Rating(this, place, value);
//        ratings.add(rating);
//        place.addRating(rating);
//    }
}
