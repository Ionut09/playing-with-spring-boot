package com.metro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.metro.domain.Movie;
import com.metro.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/allMovies")
	public List<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}

	@GetMapping("/movie")
	public Movie getMovieById(@RequestParam("id") String id) {
		return movieService.getMovieById(id);
	}

	@GetMapping("/movie/gender/{year}")
	public List<Movie> getMovieByActorsGender(@PathVariable("year") int year, @RequestParam("gender") String gender) {
		return movieService.getMovieByActorsGender(gender, year);
	}

	@PostMapping("/movie/create")
	public Movie createMovie(@RequestBody Movie movie) {
		return movieService.createMovie(movie);
	}

	@DeleteMapping("/movie/delete")
	public void deleteMovie(@RequestParam("id") String id) {
		movieService.deleteMovie(id);
	}
}
