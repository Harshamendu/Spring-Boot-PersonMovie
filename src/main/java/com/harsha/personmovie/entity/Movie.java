package com.harsha.personmovie.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Table(name = "Movie")
@Data
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5870404687095782918L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "imdb_id")
	private Long imdbId;
	@Column(name = "title")
	private String title;
	@Column(name = "story_line")
	private String storyLine;
	@Column(name = "release_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
	@Column(name = "rated")
	private String rated;
	
	
	
	
	public Movie(Integer id, Long imdbId, String title, String storyLine, Date releaseDate, String rated) {
		super();
		this.id = id;
		this.imdbId = imdbId;
		this.title = title;
		this.storyLine = storyLine;
		this.releaseDate = releaseDate;
		this.rated = rated;
	}
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStoryLine() {
		return storyLine;
	}
	public void setStoryLine(String storyLine) {
		this.storyLine = storyLine;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}

	
}
