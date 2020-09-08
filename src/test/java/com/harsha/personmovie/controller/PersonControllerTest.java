package com.harsha.personmovie.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

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
import com.harsha.personmovie.entity.Person;
import com.harsha.personmovie.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@InjectMocks
	@Spy
	private PersonController personController;

	private final String apiUrl= "/person/";
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}
	
	@Test
	 public void testFindAllPersons() throws Exception {
		ArrayList<Person> personList = new ArrayList<Person>();
		personList.add(new Person(1, 1L, "test first1", "test last1", "test sub1", "11"));
		personList.add(new Person(2, 2L, "test first2", "test last2", "test sub2", "22"));
		when(personService.findAllPersons()).thenReturn(personList);
		mockMvc.perform(MockMvcRequestBuilders.get(this.apiUrl+"getPersons").accept(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$[0].age").value("11"))
		.andExpect(jsonPath("$[1].age").value("22"))
		.andExpect(jsonPath("$[0].firstName").value("test first1"))
		.andExpect(jsonPath("$[1].firstName").value("test first2"))
		.andDo(print());
	}

	@Test
	public void testGetUser() throws Exception {
		Person person = new Person(1, 1L, "test first1", "test last1", "test sub1", "11");
		when(personService.getPersonById(1L)).thenReturn(person);
		mockMvc.perform(MockMvcRequestBuilders.get(this.apiUrl+1L).accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.lastName").value("test last1"))
		.andExpect(jsonPath("$.age").value("11"))
		.andDo(print());
	}

	@Test
	public void testAddPerson() throws Exception {
		Person person = new Person(1, 1L, "test first", "test last", "test sub", "12");
		when(personService.savePerson(person)).thenReturn(person);
		mockMvc.perform(MockMvcRequestBuilders.post(this.apiUrl+"addPersons").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(person)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testDeleteUserByID() throws Exception {
		personService.removePerson(1);
		Person person = new Person(1, 1L, "test first", "test last", "test sub", "12");
		when(personService.savePerson(person)).thenReturn(person);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(this.apiUrl+1L).accept(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(500, mvcResult.getResponse().getStatus());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
