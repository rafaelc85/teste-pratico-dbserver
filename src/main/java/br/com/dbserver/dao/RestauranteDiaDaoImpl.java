package br.com.dbserver.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.dbserver.model.RestauranteDia;
import org.joda.time.LocalDate;

@Repository("restauranteDiaDao")
public class RestauranteDiaDaoImpl extends AbstractDao<Integer, RestauranteDia> implements RestauranteDiaDao {

	public RestauranteDia findById(int id) {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", id));
            return (RestauranteDia) criteria.uniqueResult();
	}
        
        public RestauranteDia findByData(LocalDate data) {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("data", data));
            return (RestauranteDia) criteria.uniqueResult();
	}

	public void saveRestauranteDia(RestauranteDia restauranteDia) {
		persist(restauranteDia);
	}

	public void deleteRestauranteDiaById(int id) {
		Query query = getSession().createSQLQuery("delete from restaurante_dia where id = " + id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<RestauranteDia> findAllRestaurantesDia() {
		Criteria criteria = createEntityCriteria();
		return (List<RestauranteDia>) criteria.list();
	}
}
