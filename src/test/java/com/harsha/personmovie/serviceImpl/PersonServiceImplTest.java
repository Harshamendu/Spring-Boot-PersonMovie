package com.harsha.personmovie.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.harsha.personmovie.entity.Person;
import com.harsha.personmovie.repo.PersonRepo;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PersonServiceImplTest {


	@Mock
	private PersonRepo mockPersonRepo;
	
	@InjectMocks
	@Spy
	private PersonServiceImpl mockPersonService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockPersonService.personRepo = mockPersonRepo;
	}
	
	@Test
	void testFindAllPersons() {
		ArrayList<Person> personList = new ArrayList<Person>();
		personList.add(new Person(1, 1L, "test first1", "test last1", "test sub1", "11"));
		personList.add(new Person(2, 2L, "test first2", "test last2", "test sub2", "22"));
		when(mockPersonRepo.findAll()).thenReturn(personList);
		List<Person> retPersonList = mockPersonService.findAllPersons();
		assertEquals(retPersonList,personList);
	}

	@Test
	void testGetPersonById() {
		Person person = new Person(1, 1L, "test first1", "test last1", "test sub1", "11");
		when(mockPersonRepo.findByPersonId(1L)).thenReturn(Optional.of(person));
		Person retPerson = mockPersonService.getPersonById(1L);
		assertEquals(retPerson,person);
	}

	@Test
	void testSavePerson() {
		Person person = new Person(1, 1L, "test first1", "test last1", "test sub1", "11");
		when(mockPersonRepo.saveAndFlush(person)).thenReturn(person);
		Person retPerson = mockPersonService.savePerson(person);
		assertEquals(retPerson,person);
	}

	@Test
	void testRemovePerson() {
		Person person = new Person(1, 1L, "test first1", "test last1", "test sub1", "11");
		mockPersonService.removePerson(person.getId());
		verify(mockPersonRepo, times(1)).deleteById(person.getId());
	}

}
