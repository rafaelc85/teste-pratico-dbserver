package br.com.dbserver.dao;

import java.util.List;

import br.com.dbserver.model.RestauranteDia;
import org.joda.time.LocalDate;

public interface RestauranteDiaDao {

	RestauranteDia findById(int id);
        
        RestauranteDia findByData(LocalDate data);

	void saveRestauranteDia(RestauranteDia restauranteDia);
	
	void deleteRestauranteDiaById(int id);
	
	List<RestauranteDia> findAllRestaurantesDia();

}
