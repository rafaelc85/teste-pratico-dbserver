package br.com.dbserver.service;

import java.util.List;

import br.com.dbserver.model.RestauranteDia;
import org.joda.time.LocalDate;

public interface RestauranteDiaService {

	RestauranteDia findById(int id);
        
        RestauranteDia findByData(LocalDate data);
	
	void saveRestauranteDia(RestauranteDia restauranteDia);
	
	void updateRestauranteDia(RestauranteDia restauranteDia);
	
	void deleteRestauranteDiaById(int id);

	List<RestauranteDia> findAllRestaurantesDia(); 
	
}
