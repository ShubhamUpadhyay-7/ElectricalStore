package com.wipro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	Optional<State> findByStateName(String stateName);

}
