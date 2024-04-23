package com.wipro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.City;
import com.wipro.model.State;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	Optional<City> findByCityNameAndState(String cityName, State state);
}

