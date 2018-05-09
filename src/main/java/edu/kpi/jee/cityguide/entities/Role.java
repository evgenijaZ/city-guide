package edu.kpi.jee.cityguide.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="r_id")
	private int id;
	@Column(name="r_role")
	private String role;
	
}
