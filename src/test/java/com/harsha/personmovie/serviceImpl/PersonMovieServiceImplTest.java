package com.harsha.personmovie.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.harsha.personmovie.entity.Movie;
import com.harsha.personmovie.entity.Person;
import com.harsha.personmovie.entity.PersonMovie;
import com.harsha.personmovie.repo.PersonMovieRepo;
import com.harsha.personmovie.service.MovieService;
import com.harsha.personmovie.service.PersonService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PersonMovieServiceImplTest {
	
	@Mock
	private PersonMovieRepo mockPersonMovieRepo;

	@Mock
	private PersonService mockPersonService;

	@Mock
	private MovieService mockMovieService;
	
	@InjectMocks
	@Spy
	private PersonMovieServiceImpl mockPersonMovieService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockPersonMovieService.movieService = mockMovieService;
		mockPersonMovieService.personService = mockPersonService;
		mockPersonMovieService.personMovieRepo = mockPersonMovieRepo;
	}
	
	
	@Test
	void testFindAllPersonMovie() {
		ArrayList<Person> personList = new ArrayList<>();
		personList.add(new Person(1, 1L, "test first1", "test last1", "test sub1", "11"));
		personList.add(new Person(2, 2L, "test first2", "test last2", "test sub2", "22"));
		Movie movie1 = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		Movie movie2 = new Movie(2,3214L,"Test title2","Test synopsis2",new Date(),"PG-15");
		
		
		List<PersonMovie> perMovRel1 = new ArrayList<>();
		perMovRel1.add(new PersonMovie(1,3453L,1L));
		List<PersonMovie> perMovRel2 = new ArrayList<>();
		perMovRel2.add(new PersonMovie(1,3453L,2L));
		perMovRel2.add(new PersonMovie(2,3214L,2L));
		when(mockPersonMovieRepo.findByPersonId(1L)).thenReturn(perMovRel1);
		when(mockPersonMovieRepo.findByPersonId(2L)).thenReturn(perMovRel2);
		when(mockPersonService.findAllPersons()).thenReturn(personList);
		when(mockMovieService.getMovieByImdbId(3453L)).thenReturn(movie1);
		when(mockMovieService.getMovieByImdbId(3214L)).thenReturn(movie2);
		
		List<Person> retPersonList = mockPersonMovieService.findAllPersonMovie();
		
		ArrayList<Movie> movieList1 = new ArrayList<>();
		movieList1.add(movie1);
		ArrayList<Movie> movieList2 = new ArrayList<>();
		movieList2.add(movie1);
		movieList2.add(movie2);
		personList.get(0).setMovieList(movieList1);
		personList.get(1).setMovieList(movieList2);
		
		assertEquals(retPersonList,personList);
		
		
	}

	@Test
	void testFindPersonMoviePersonId() {
		
		Person p1 = new Person(1, 1L, "test first1", "test last1", "test sub1", "11");
		Movie movie1 = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		Movie movie2 = new Movie(2,3214L,"Test title2","Test synopsis2",new Date(),"PG-15");

		List<PersonMovie> perMovRel1 = new ArrayList<>();
		perMovRel1.add(new PersonMovie(1,3453L,1L));
		perMovRel1.add(new PersonMovie(2,3214L,1L));
		when(mockPersonMovieRepo.findByPersonId(1L)).thenReturn(perMovRel1);
		when(mockPersonService.getPersonById(1L)).thenReturn(p1);
		when(mockMovieService.getMovieByImdbId(3453L)).thenReturn(movie1);
		when(mockMovieService.getMovieByImdbId(3214L)).thenReturn(movie2);
		
		Person retPerson= mockPersonMovieService.findPersonMoviePersonId(1L);
		
		ArrayList<Movie> movieList = new ArrayList<>();
		movieList.add(movie1);
		movieList.add(movie2);
		p1.setMovieList(movieList);
		
		assertEquals(retPerson,p1);
	}

}
