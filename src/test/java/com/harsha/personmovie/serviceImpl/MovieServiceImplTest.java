package com.harsha.personmovie.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.harsha.personmovie.repo.MovieRepo;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MovieServiceImplTest {

	@Mock
	private MovieRepo mockMovieRepo;
	
	@InjectMocks
	@Spy
	private MovieServiceImpl mockMovieService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMovieService.movieRepo = mockMovieRepo;
	}
	
	@Test
	void testFindAllMovies() {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13"));
		movieList.add(new Movie(2,3214L,"Test title2","Test synopsis2",new Date(),"PG-15"));
		when(mockMovieRepo.findAll()).thenReturn(movieList);
		List<Movie> retMovieList = mockMovieService.findAllMovies();
		assertEquals(retMovieList,movieList);
	}

	@Test
	void testGetMovieByImdbId() {
		Movie movie = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		when(mockMovieRepo.findByImdbId(3453L)).thenReturn(Optional.of(movie));
		Movie retMovie = mockMovieService.getMovieByImdbId(3453L);
		assertEquals(retMovie,movie);
	}

	@Test
	void testSaveMovie() {
		Movie movie = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		when(mockMovieRepo.saveAndFlush(movie)).thenReturn(movie);
		Movie retMovie = mockMovieService.saveMovie(movie);
		assertEquals(retMovie,movie);
	}

	@Test
	void testRemoveMovie() {	
		Movie movie = new Movie(1,3453L,"Test title1","Test synopsis1",new Date(),"PG-13");
		mockMovieService.removeMovie(movie.getId());
		verify(mockMovieRepo, times(1)).deleteById(movie.getId());
	}

}
