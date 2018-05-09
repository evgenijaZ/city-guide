package edu.kpi.jee.cityguide.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "u_id")
    private int id;
    @Column(name = "u_username")
    private String name;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    @Column(name = "u_email")
    private String email;
    @Length(min = 6, message = "*Your password must have at least 6 characters")
    @NotEmpty(message = "*Please provide your password")
    @Column(name = "u_password")
    @Transient
    private String password;
    @Column(name = "u_active")
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "m2m_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    private Set<Role> roles;

//    @OneToMany(mappedBy = "user")
//    @JsonManagedReference
//    private Set<Rating> ratings;

//    public void ratePlace(Place place, float value) {
//        Rating rating = new Rating(this, place, value);
//        ratings.add(rating);
//        place.addRating(rating);
//    }
}
