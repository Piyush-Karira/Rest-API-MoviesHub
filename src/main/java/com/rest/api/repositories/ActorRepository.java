package com.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.models.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	Actor findById(int actorId);
}
