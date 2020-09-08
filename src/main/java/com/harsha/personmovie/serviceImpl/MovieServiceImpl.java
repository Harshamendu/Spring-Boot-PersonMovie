package com.harsha.personmovie.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.harsha.personmovie.entity.Movie;
import com.harsha.personmovie.repo.MovieRepo;
import com.harsha.personmovie.service.MovieService;

@Service("MovieService")
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepo movieRepo;
	
	@Override
	public List<Movie> findAllMovies() {
		 Iterable<Movie> movieItr = movieRepo.findAll();
		 List<Movie> movieList = new ArrayList<>();
		 movieItr.forEach(s -> movieList.add(s));
		if (CollectionUtils.isEmpty(movieList))
			return null;
		else
			return movieList;
	}

	@Override
	public Movie getMovieByImdbId(Long imdbId) {
		Optional<Movie> optMovie = movieRepo.findByImdbId(imdbId);
		if(optMovie.isPresent())
			return optMovie.get();
		else 
			return null;
	}

	@Override
	public Movie saveMovie(Movie movie) {
		Movie retMovie = movieRepo.saveAndFlush(movie);
		return retMovie;
	}

	@Override
	public void removeMovie(Integer id) {
		movieRepo.deleteById(id);
	}

}
