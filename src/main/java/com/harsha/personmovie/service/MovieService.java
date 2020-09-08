package com.harsha.personmovie.service;

import java.util.List;

import com.harsha.personmovie.entity.Movie;

public interface MovieService {

	List<Movie> findAllMovies();

	Movie getMovieByImdbId(Long imdbId);

	Movie saveMovie(Movie movie);

	void removeMovie(Integer id);

}
