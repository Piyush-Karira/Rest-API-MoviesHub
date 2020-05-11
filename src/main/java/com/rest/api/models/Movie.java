package com.rest.api.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Movie {
	@Id
	@GeneratedValue
	int id;
	@Column(unique = true, nullable = false)
	String title;
	@Column(nullable = false)
	Date release_date;

	@JsonIgnoreProperties("movies")
	@ManyToMany(mappedBy = "movies", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	Set<Actor> actors = new HashSet<Actor>();
	public Movie() {
	}

	public Movie(String title, Date release_date) {
		super();
		this.title = title;
		this.release_date = release_date;
	}


	public String getTitle() {
		return title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public void addActors(Actor actor) {
		this.getActors().add(actor);
		actor.getMovies().add(this);
	}

	public void deleteActors(Actor actor) {
		this.getActors().remove(actor);
		actor.getMovies().remove(this);
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", release_date=" + release_date + "]";
	}
}
