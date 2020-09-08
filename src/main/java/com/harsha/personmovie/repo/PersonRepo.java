package com.harsha.personmovie.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsha.personmovie.entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

	Optional<Person> findByPersonId(Long personId);

}
