package com.wipro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.City;
import com.wipro.model.DealerProduct;
import com.wipro.model.State;

@Repository
public interface DealerProductRepository extends JpaRepository<DealerProduct, Long> {

	List<DealerProduct> findByDealerName(String dealerName);

	List<DealerProduct> findByStateAndCity(State state, City city);

	List<DealerProduct> findByState(State state);
}

