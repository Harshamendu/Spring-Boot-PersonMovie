package com.harsha.personmovie.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.harsha.personmovie.entity.Person;
import com.harsha.personmovie.repo.PersonRepo;
import com.harsha.personmovie.service.PersonService;

@Service("PersonService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepo personRepo;
	
	@Override
	public List<Person> findAllPersons() {
		 Iterable<Person> personItr = personRepo.findAll();
		 List<Person> personList = new ArrayList<>();
		 personItr.forEach(s -> personList.add(s));
		if (CollectionUtils.isEmpty(personList))
			return null;
		else
			return personList;
	}

	@Override
	public Person getPersonById(Long personId) {
		Optional<Person> optPerson = personRepo.findByPersonId(personId);
		if(optPerson.isPresent())
			return optPerson.get();
		else 
			return null;
	}

	@Override
	public Person savePerson(Person person) {
		Person retPerson = personRepo.saveAndFlush(person);
		return retPerson;
	}

	@Override
	public void removePerson(Integer id) {
		personRepo.deleteById(id);
	}

}
