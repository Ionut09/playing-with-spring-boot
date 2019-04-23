package com.metro.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metro.domain.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

	List<Movie> findByName(String name);

}
