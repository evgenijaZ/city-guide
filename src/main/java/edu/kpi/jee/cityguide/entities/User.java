package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

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
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Rating> ratings;


    public User(String userName, String email, String password, Set<Rating> ratings) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.ratings = ratings;
    }

    public User(int id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public void ratePlace(Place place, float value) {
        Rating rating = new Rating(this, place, value);
        ratings.add(rating);
        place.addRating(rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
