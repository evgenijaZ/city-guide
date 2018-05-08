//package edu.kpi.jee.cityguide.entities;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "rating")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//public class Rating {
//    @Id
//    @Column(name = "r_id")
//    private int id;
//    @Column(name = "r_value")
//    private float value;
//    @ManyToOne
//    @JoinColumn(name = "r_user_id", nullable = false)
//    @JsonBackReference
//    private User user;
//    @ManyToOne
//    @JoinColumn(name = "r_place_id", nullable = false)
//    @JsonBackReference
//    private Place place;
//}
