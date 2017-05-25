package br.com.dbserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbserver.dao.RestauranteDao;
import br.com.dbserver.model.Restaurante;

@Service("restauranteService")
@Transactional
public class RestauranteServiceImpl implements RestauranteService {

	@Autowired
	private RestauranteDao dao;
	
	public Restaurante findById(int id) {
		return dao.findById(id);
	}

	public void saveRestaurante(Restaurante restaurante) {
		dao.saveRestaurante(restaurante);
	}

	public void updateRestaurante(Restaurante restaurante) {
		Restaurante entity = dao.findById(restaurante.getId());
		if(entity!=null){
			entity.setNome(restaurante.getNome());
		}
	}

	public void deleteRestauranteById(int id) {
		dao.deleteRestauranteById(id);
	}
	
	public List<Restaurante> findAllRestaurantes() {
		return dao.findAllRestaurantes();
	}
	
}
