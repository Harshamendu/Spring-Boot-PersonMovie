package com.harsha.personmovie.service;

import java.util.List;

import com.harsha.personmovie.entity.Person;

public interface PersonService {

	List<Person> findAllPersons();

	Person getPersonById(Long personId);

	Person savePerson(Person person);

	void removePerson(Integer id);

}
