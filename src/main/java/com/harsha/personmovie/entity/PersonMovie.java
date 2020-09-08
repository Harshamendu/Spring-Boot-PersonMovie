package com.harsha.personmovie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "person_movie")
public class PersonMovie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -335381439475849057L;

	
	
	
	public PersonMovie(Integer id, Long imdbId, Long personId) {
		super();
		this.id = id;
		this.imdbId = imdbId;
		this.personId = personId;
	}

	public PersonMovie() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="imdb_id")
	private Long imdbId;

	@Column(name="person_id")
	private Long personId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getImdbId() {
		return imdbId;
	}

	public void setImdbId(Long imdbId) {
		this.imdbId = imdbId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	
}
