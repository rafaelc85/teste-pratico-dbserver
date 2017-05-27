package br.com.dbserver.dao;

import br.com.dbserver.model.Funcionario;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.dbserver.model.Voto;

@Repository("votoDao")
public class VotoDaoImpl extends AbstractDao<Integer, Voto> implements VotoDao {

	public Voto findById(int id) {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", id));
            return (Voto) criteria.uniqueResult();
	}

	public void saveVoto(Voto voto) {
		persist(voto);
	}

	public void deleteVotoById(int id) {
		Query query = getSession().createSQLQuery("delete from voto where id = " + id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Voto> findAllVotos() {
            Criteria criteria = createEntityCriteria();
            return (List<Voto>) criteria.list();
	}

        public List<Voto> findVotosByFuncionario(Funcionario funcionario) {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("funcionario", funcionario));
            return (List<Voto>) criteria.list();  
        }
}
