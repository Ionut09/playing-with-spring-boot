package com.metro.domain;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "movie_info")
@Data
public class Movie implements Serializable {

	@Id
	@Column(name = "movie_id")
	private String id;

	@Column(name = "movie_name")
	private String name;

	@Column(name = "year")
	private int year;

	@Column(name = "rating")
	private int rating;

	@ManyToMany(fetch = EAGER)
	@JoinTable(name="actor_movies",
			joinColumns = @JoinColumn(name = "movie_id", referencedColumnName="movie_id"), 
			inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName="actor_id"))
	private List<Actor> actors = new ArrayList<>();
}
