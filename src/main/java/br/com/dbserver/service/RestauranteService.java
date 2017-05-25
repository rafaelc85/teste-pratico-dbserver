package br.com.dbserver.service;

import java.util.List;

import br.com.dbserver.model.Restaurante;

public interface RestauranteService {

	Restaurante findById(int id);
	
	void saveRestaurante(Restaurante restaurante);
	
	void updateRestaurante(Restaurante restaurante);
	
	void deleteRestauranteById(int id);

	List<Restaurante> findAllRestaurantes(); 
	
}
