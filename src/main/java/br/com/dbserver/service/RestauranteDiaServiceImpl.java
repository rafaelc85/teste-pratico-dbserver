package br.com.dbserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbserver.dao.RestauranteDiaDao;
import br.com.dbserver.model.RestauranteDia;
import org.joda.time.LocalDate;

@Service("restauranteDiaService")
@Transactional
public class RestauranteDiaServiceImpl implements RestauranteDiaService {

	@Autowired
	private RestauranteDiaDao dao;
	
	public RestauranteDia findById(int id) {
		return dao.findById(id);
	}
        
        public RestauranteDia findByData(LocalDate data) {
		return dao.findByData(data);
	}

	public void saveRestauranteDia(RestauranteDia restauranteDia) {
		dao.saveRestauranteDia(restauranteDia);
	}

	public void updateRestauranteDia(RestauranteDia restauranteDia) {
		RestauranteDia entity = dao.findById(restauranteDia.getId());
		if(entity!=null){
                        entity.setRestaurante(restauranteDia.getRestaurante());
                        entity.setData(restauranteDia.getData());
		}
	}

	public void deleteRestauranteDiaById(int id) {
		dao.deleteRestauranteDiaById(id);
	}
	
	public List<RestauranteDia> findAllRestaurantesDia() {
		return dao.findAllRestaurantesDia();
	}
	
}
