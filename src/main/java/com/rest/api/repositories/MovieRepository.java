package com.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Movie findById(int movieId);
}
