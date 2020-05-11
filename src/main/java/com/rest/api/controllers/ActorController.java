package com.rest.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.exception.NotFoundException;
import com.rest.api.models.Actor;
import com.rest.api.repositories.ActorRepository;
import com.rest.api.repositories.MovieRepository;

@RestController
@RequestMapping("/actors")
public class ActorController {

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	MovieRepository movieRepository;
	Logger logger = LoggerFactory.getLogger(ActorController.class);

	public void actorValidation(int actorId) {
		if (!actorRepository.existsById(actorId)) {
			throw new NotFoundException("actor " + actorId + " Not found");
		}
	}

	@PostMapping("")
	public Actor createActor(@Valid @RequestBody Actor actor) {
		logger.info("{}", actor);
		return actorRepository.save(actor);
	}

	@GetMapping("")
	List<Actor> getActors() {
		return actorRepository.findAll();
	}

	@PutMapping("/{id}")
	public Actor updateActor(@PathVariable(value = "id") int actorId, @Valid @RequestBody Actor actorRequest) {
		this.actorValidation(actorId);
		Actor actor = actorRepository.findById(actorId);
		actor.setAge(actorRequest.getAge());
		actor.setName(actorRequest.getName());
		actor.setGender(actor.getGender());
		return actorRepository.save(actor);

	}

	@GetMapping("/{id}")
	public Actor getActor(@PathVariable("id") int actorId) {
		actorValidation(actorId);
		return actorRepository.findById(actorId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteActor(@PathVariable("id") int actorId) {
		this.actorValidation(actorId);

		actorRepository.deleteById(actorId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}/movies")
	public Actor putActorMovies(@Valid @PathVariable(value="id") int actorId, @RequestBody HashMap<String, ArrayList<Integer>> movies) {
		this.actorValidation(actorId);
		ArrayList<Integer> movieIds= movies.get("movies");
		
		Actor actor = actorRepository.findById(actorId);
		for (int movieId : movieIds) {
			if (!movieRepository.existsById(movieId))
				throw new NotFoundException("Movie " + movieId + " Not found");

			actor.addMovies(movieRepository.findById(movieId));
		}
		return actorRepository.save(actor);
	}

	@DeleteMapping("/{id}/movies")
	public Actor deleteActorMovies(@Valid @PathVariable(value = "id") int actorId,
			@RequestBody HashMap<String, ArrayList<Integer>> movies) {
		ArrayList<Integer> movieIds = movies.get("movies");
		this.actorValidation(actorId);

		Actor actor = actorRepository.findById(actorId);
		for (int movieId : movieIds) {
			if (!movieRepository.existsById(movieId))
				throw new NotFoundException("Movie " + movieId + " Not found");
			actor.deleteMovies(movieRepository.findById(movieId));
		}
		return actorRepository.save(actor);
	}

}
