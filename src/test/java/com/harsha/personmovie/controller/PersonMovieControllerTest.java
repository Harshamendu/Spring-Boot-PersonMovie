package com.harsha.personmovie.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.harsha.personmovie.entity.Movie;
import com.harsha.personmovie.entity.Person;
import com.harsha.personmovie.service.PersonMovieService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonMovieControllerTest {

	private MockMvc mockMvc;
	
	@MockBean
	private PersonMovieService personMovieService;
	
	@InjectMocks
	@Spy
	private PersonMovieController personMovieController;

	private final String apiUrl= "/personMovie/";
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(personMovieController).build();
	}

	@Test
	public void testFindPersonMovie() throws Exception {
		Person p1 = new Person(1, 1L, "test first1", "test last1", "test sub1", "11");
		Person p2 = new Person(2, 2L, "test first2", "test last2", "test sub2", "22");
		Movie m1 = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		Movie m2 = new Movie(2,3214L,"Test title2","Test synopsis2",new Date(),"PG-15");
		ArrayList<Person> personList = new ArrayList<Person>();
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(m1);
		p1.setMovieList(movieList);
		movieList.add(m2);
		p2.setMovieList(movieList);
		personList.add(p1);
		personList.add(p2);
		when(personMovieService.findAllPersonMovie()).thenReturn(personList);
		mockMvc.perform(MockMvcRequestBuilders.get(this.apiUrl+"getAllPersonMovie").accept(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$[0].age").value("11"))
		.andExpect(jsonPath("$[1].age").value("22"))
		.andExpect(jsonPath("$[0].firstName").value("test first1"))
		.andExpect(jsonPath("$[1].firstName").value("test first2"))
		.andExpect(jsonPath("$[0].movieList[0].title").value("Test title1"))
		.andExpect(jsonPath("$[0].movieList[0].rated").value("PG-13"))
		.andExpect(jsonPath("$[1].movieList[0].title").value("Test title1"))
		.andExpect(jsonPath("$[1].movieList[1].storyLine").value("Test synopsis2"))
		.andDo(print());
	}

	@Test
	public void testFindPersonMovieByPersonId() throws Exception {
		Person person = new Person(1, 1L, "test first1", "test last1", "test sub1", "11");
		Movie m1 = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		Movie m2 = new Movie(2,3214L,"Test title2","Test synopsis2",new Date(),"PG-15");
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(m1);
		movieList.add(m2);
		person.setMovieList(movieList);
		when(personMovieService.findPersonMoviePersonId(1L)).thenReturn(person);
		mockMvc.perform(MockMvcRequestBuilders.get(this.apiUrl+"getPersonMovie?personId=1").accept(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$.age").value("11"))
		.andExpect(jsonPath("$.firstName").value("test first1"))
		.andExpect(jsonPath("$.movieList[0].title").value("Test title1"))
		.andExpect(jsonPath("$.movieList[0].rated").value("PG-13"))
		.andExpect(jsonPath("$.movieList[1].title").value("Test title2"))
		.andExpect(jsonPath("$.movieList[1].storyLine").value("Test synopsis2"))
		.andDo(print());
	}

}
