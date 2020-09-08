package com.harsha.personmovie.service;

import java.util.List;

import com.harsha.personmovie.entity.Person;

public interface PersonMovieService {

	List<Person> findAllPersonMovie();

	Person findPersonMoviePersonId(Long valueOf);

}
