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

import com.harsha.personmovie.entity.Person;
import com.harsha.personmovie.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/getPersons")
	public @ResponseBody ResponseEntity<List<Person>> findAllPersons(){
		 List<Person> personList = personService.findAllPersons();
		 return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{personId}", method = RequestMethod.GET)
	public @ResponseBody Person getUser(@PathVariable Long personId) {
		Person person = personService.getPersonById(personId);
		return person;
	}

	@PostMapping(value = "/addPersons", produces =MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseEntity<Person> addPerson(@RequestBody Person person){
		System.out.println(" ****** From server 1 ********");
		 Person savedPerson= personService.savePerson(person);
		 return new ResponseEntity<Person>(savedPerson, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{personId}")
	public ResponseEntity<?> deleteUserByID(@PathVariable Long personId) {
		Person person = personService.getPersonById(personId);
		if (null != person)
			personService.removePerson(person.getId());
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
