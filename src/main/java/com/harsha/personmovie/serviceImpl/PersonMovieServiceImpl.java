package com.harsha.personmovie.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsha.personmovie.entity.Movie;
import com.harsha.personmovie.entity.Person;
import com.harsha.personmovie.entity.PersonMovie;
import com.harsha.personmovie.repo.PersonMovieRepo;
import com.harsha.personmovie.service.MovieService;
import com.harsha.personmovie.service.PersonMovieService;
import com.harsha.personmovie.service.PersonService;

@Service("PersonMovieService")
public class PersonMovieServiceImpl implements PersonMovieService {

	@Autowired
	PersonMovieRepo personMovieRepo;

	@Autowired
	PersonService personService;

	@Autowired
	MovieService movieService;

	@Override
	public List<Person> findAllPersonMovie() {

		List<Person> personList = personService.findAllPersons();

		personList.forEach(persMovie -> {
			List<PersonMovie> perMovRel = personMovieRepo.findByPersonId(persMovie.getPersonId());
			List<Movie> movieList = new ArrayList<>();
			perMovRel.forEach(perMovR -> {
				Movie movie = movieService.getMovieByImdbId(perMovR.getImdbId());
				movieList.add(movie);
			});
			persMovie.setMovieList(movieList);
		});

		return personList;

	}

	@Override
	public Person findPersonMoviePersonId(Long personId) {
		Person person = personService.getPersonById(personId);

		List<PersonMovie> perMovRel = personMovieRepo.findByPersonId(person.getPersonId());
		List<Movie> movieList = new ArrayList<>();
		perMovRel.forEach(perMovR -> {
			Movie movie = movieService.getMovieByImdbId(perMovR.getImdbId());
			movieList.add(movie);
		});
		person.setMovieList(movieList);
		return person;
	}

}
