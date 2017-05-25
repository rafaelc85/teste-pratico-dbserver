package br.com.dbserver.dao;

import java.util.List;

import br.com.dbserver.model.Restaurante;

public interface RestauranteDao {

	Restaurante findById(int id);

	void saveRestaurante(Restaurante restaurante);
	
	void deleteRestauranteById(int id);
	
	List<Restaurante> findAllRestaurantes();

}
