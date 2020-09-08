package com.harsha.personmovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harsha.personmovie.entity.Person;
import com.harsha.personmovie.service.PersonMovieService;

@Controller
@RequestMapping("/personMovie")
public class PersonMovieController {


	@Autowired
	PersonMovieService personMovieService;
	
	@GetMapping("/getAllPersonMovie")
	public @ResponseBody ResponseEntity<List<Person>> findPersonMovie() {
		List<Person> personList = personMovieService.findAllPersonMovie();
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}
	
	@GetMapping("/getPersonMovie")
	public @ResponseBody ResponseEntity<Person> findPersonMovieByPersonId(@RequestParam String personId) {
		Person person = personMovieService.findPersonMoviePersonId(Long.valueOf(personId));
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
}
