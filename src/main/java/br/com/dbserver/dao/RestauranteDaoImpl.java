package br.com.dbserver.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;

@Repository("restauranteDao")
public class RestauranteDaoImpl extends AbstractDao<Integer, Restaurante> implements RestauranteDao {

	public Restaurante findById(int id) {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", id));
            return (Restaurante) criteria.uniqueResult();
	}

	public void saveRestaurante(Restaurante restaurante) {
		persist(restaurante);
	}

	public void deleteRestauranteById(int id) {
		Query query = getSession().createSQLQuery("delete from restaurante where id = " + id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Restaurante> findAllRestaurantes() {
		Criteria criteria = createEntityCriteria();
		return (List<Restaurante>) criteria.list();
	}
}
