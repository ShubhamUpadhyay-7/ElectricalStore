package com.wipro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.City;
import com.wipro.model.State;
import com.wipro.model.DealerProduct;
import com.wipro.repository.CityRepository;
import com.wipro.repository.DealerProductRepository;
import com.wipro.repository.StateRepository;
import com.wipro.exception.*;

import java.util.List;
import java.util.Optional;

@Service
public class DealerProductService {

    private final DealerProductRepository dealerProductRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    @Autowired
    public DealerProductService(DealerProductRepository dealerProductRepository,
                               StateRepository stateRepository,
                               CityRepository cityRepository) {
        this.dealerProductRepository = dealerProductRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }

    public List<DealerProduct> getDealerProductByStateAndCity(String stateName, String cityName) {
        Optional<State> stateOptional = stateRepository.findByStateName(stateName);
        if (stateOptional.isEmpty()) {
            throw new NotFoundException("State not found: " + stateName);
        }

        Optional<City> cityOptional = cityRepository.findByCityNameAndState(cityName, stateOptional.get());
        if (cityOptional.isEmpty()) {
            throw new NotFoundException("City not found: " + cityName + " in state " + stateName);
        }

        return dealerProductRepository.findByStateAndCity(stateOptional.get(), cityOptional.get());
    }

    public List<DealerProduct> getDealersProductByState(String stateName) {
        // Add any additional business logic/validation if needed
        Optional<State> stateOptional = stateRepository.findByStateName(stateName);
        if (stateOptional.isEmpty()) {
            throw new NotFoundException("State not found: " + stateName);
        }

        return dealerProductRepository.findByState(stateOptional.get());
    }
}



