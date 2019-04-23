package com.metro.domain;

import static javax.persistence.EnumType.STRING;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "actors")
@Data
public class Actor implements Serializable {

	@Id
	@Column(name = "actor_id")
	private String id;

	@Column(name = "actor_name")
	private String name;

	@Column(name = "gender")
	@Enumerated(STRING)
	private Gender gender;

	@ManyToMany(mappedBy = "actors")
	@JsonIgnore
	private List<Movie> movies = new ArrayList<>();

}
