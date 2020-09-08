package com.harsha.personmovie.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsha.personmovie.entity.Movie;
import com.harsha.personmovie.service.MovieService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

	private MockMvc mockMvc;
	
	@MockBean
	private MovieService movieService;
	
	@InjectMocks
	@Spy
	private MovieController movieController;

	private final String apiUrl= "/movie/";
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
	}

	@Test
	public void testFindAllMovies() throws Exception {
		
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13"));
		movieList.add(new Movie(2,3214L,"Test title2","Test synopsis2",new Date(),"PG-15"));
		when(movieService.findAllMovies()).thenReturn(movieList);
		mockMvc.perform(MockMvcRequestBuilders.get(this.apiUrl+"getMovies").accept(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$[0].rated").value("PG-13"))
		.andExpect(jsonPath("$[1].rated").value("PG-15"))
		.andExpect(jsonPath("$[0].imdbId").value(3453L))
		.andExpect(jsonPath("$[1].imdbId").value(3214L))
		.andDo(print());
	}

	@Test
	public void testGetMovie() throws Exception {
		Movie movie = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		when(movieService.getMovieByImdbId(3453L)).thenReturn(movie);
		mockMvc.perform(MockMvcRequestBuilders.get(this.apiUrl+3453L).accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.imdbId").value(3453L))
		.andExpect(jsonPath("$.rated").value("PG-13"))
		.andDo(print());
	}

	@Test
	public void testAddMovie() throws Exception {
		Movie movie = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		when(movieService.saveMovie(movie)).thenReturn(movie);
		mockMvc.perform(MockMvcRequestBuilders.post(this.apiUrl+"addMovies").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(movie)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testDeleteUserByID() throws Exception {
		movieService.removeMovie(1);
		Movie movie = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		when(movieService.saveMovie(movie)).thenReturn(movie);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(this.apiUrl+3453L).accept(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(502, mvcResult.getResponse().getStatus());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
