package com.harsha.personmovie.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harsha.personmovie.entity.Movie;
import com.harsha.personmovie.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieService movieService;

	@GetMapping("/getMovies")
	public @ResponseBody ResponseEntity<List<Movie>> findAllMovies() {
		List<Movie> MovieList = movieService.findAllMovies();
		return new ResponseEntity<List<Movie>>(MovieList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{imdbId}", method = RequestMethod.GET)
	public @ResponseBody Movie getMovie(@PathVariable Long imdbId) {
		Movie movie = movieService.getMovieByImdbId(imdbId);
		return movie;
	}

	@PostMapping(value = "/addMovies", produces = MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		Movie savedMovie = movieService.saveMovie(movie);
		return new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
	}

	
	@DeleteMapping("/{imdbId}")
	public ResponseEntity<?> deleteUserByID(@PathVariable Long imdbId) {
		Movie movie = movieService.getMovieByImdbId(imdbId);
		if(null != movie)
		movieService.removeMovie(movie.getId());
		else
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
