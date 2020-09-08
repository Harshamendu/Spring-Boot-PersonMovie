package com.harsha.personmovie.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsha.personmovie.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

	Optional<Movie> findByImdbId(Long imdbId);


}
