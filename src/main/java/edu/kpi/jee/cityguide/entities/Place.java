package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "place")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Place {
    @Id
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
    @Column(name = "p_lat")
    private float latitude;
    @Column(name = "p_lng")
    private float longitude;
    @OneToMany(mappedBy = "place")
    @JsonManagedReference
    private Set<Rating> ratings;

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

    public Place() {
    }

    public Place(String name, String address, float latitude, float longitude) {
        id = -1;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(int id, String name, String address, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(int id, String name, String address, float latitude, float longitude, Set<Rating> ratings, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ratings = ratings;
        this.categories = categories;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public float getRating() {
        float resultRating = 0;
        for (Rating rating : ratings) {
            resultRating += rating.getValue() / ratings.size();
        }
        return resultRating;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Place place = (Place) obj;
        return (Math.abs(latitude - place.getLatitude()) < 0.00001 &&
                Math.abs(longitude - place.getLongitude()) < 0.00001 &&
                name.equals(place.getName()) &&
                address.equals(place.getAddress()));
    }

    @Override
    public int hashCode() {
        return (int) (longitude * 1000 + latitude);
    }
}
