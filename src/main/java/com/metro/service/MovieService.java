package com.metro.service;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metro.domain.Actor;
import com.metro.domain.Movie;
import com.metro.repository.MovieRepository;

import static java.util.stream.Collectors.toList;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepo;

	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		movieRepo.findAll()
				// .forEach(movie -> movies.add(movie));
				.forEach(movies::add);
		// .forEach(String::isEmpty);
		return movies;
	}

	public Movie getMovieById(String id) {
		return movieRepo.findById(id).orElse(new Movie());
	}

	@Transactional(propagation = REQUIRED)
	public Movie createMovie(Movie movie) {
		return movieRepo.save(movie);
	}

	public void deleteMovie(String id) {
		Movie movie = movieRepo.findById(id).get();
		movieRepo.delete(movie);
	}

	public List<Movie> getMovieByActorsGender(String gender, int year) {
		return getAllMovies() // nu asa
				.parallelStream()
				.filter(movie -> hasActorsOfThisGender(movie, gender))
				.filter(movie -> movie.getYear() < year)
				.collect(toList());
	}

	private boolean hasActorsOfThisGender(Movie movie, String gender) {
		List<Actor> actors = movie.getActors();
		if (actors == null || actors.isEmpty()) {
			return false;
		}
		return actors.stream().allMatch(actor -> actor.getGender().toString().equals(gender));
	}

}
