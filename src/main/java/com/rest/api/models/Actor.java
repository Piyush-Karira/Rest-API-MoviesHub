package com.rest.api.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Actor {

	@Id
	@GeneratedValue
	int id;
	
	@Column(nullable = false)
	String name;

	@Column(nullable = false)
	String gender;

	@Column(nullable = false)
	int age;

	@JsonIgnoreProperties("actors")
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "actor_movie", joinColumns = { @JoinColumn(name = "actor_id") }, inverseJoinColumns = {
			@JoinColumn(name = "movie_id") })
	Set<Movie> movies = new HashSet<Movie>();
	public Actor() {
	}

	public Actor(String name, String gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public void addMovies(Movie movie) {
		this.getMovies().add(movie);
		movie.getActors().add(this);
	}

	public void deleteMovies(Movie movie) {
		this.getMovies().remove(movie);
		movie.getActors().remove(this);
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", Gender=" + gender + ", age=" + age + "]";
	}
}
