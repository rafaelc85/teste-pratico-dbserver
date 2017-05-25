package br.com.dbserver.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.dbserver.model.Funcionario;

@Repository("funcionarioDao")
public class FuncionarioDaoImpl extends AbstractDao<Integer, Funcionario> implements FuncionarioDao {

	public Funcionario findById(int id) {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", id));
            return (Funcionario) criteria.uniqueResult();
	}

	public void saveFuncionario(Funcionario funcionario) {
		persist(funcionario);
	}

	public void deleteFuncionarioById(int id) {
		Query query = getSession().createSQLQuery("delete from funcionario where id = " + id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> findAllFuncionarios() {
		Criteria criteria = createEntityCriteria();
		return (List<Funcionario>) criteria.list();
	}
}
