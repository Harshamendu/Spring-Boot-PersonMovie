package com.harsha.personmovie.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsha.personmovie.entity.PersonMovie;

@Repository
public interface PersonMovieRepo extends JpaRepository<PersonMovie, Integer> {

	List<PersonMovie> findByPersonId(Long personId);

}
