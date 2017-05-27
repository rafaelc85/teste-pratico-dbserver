package br.com.dbserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbserver.dao.VotoDao;
import br.com.dbserver.model.Voto;

@Service("votoService")
@Transactional
public class VotoServiceImpl implements VotoService {

	@Autowired
	private VotoDao dao;
	
	public Voto findById(int id) {
		return dao.findById(id);
	}

	public void saveVoto(Voto voto) {
		dao.saveVoto(voto);
	}

	public void updateVoto(Voto voto) {
		Voto entity = dao.findById(voto.getId());
		if(entity!=null){
			entity.setData(voto.getData());;
                        entity.setFuncionario(voto.getFuncionario());
                        entity.setRestaurante(voto.getRestaurante());
		}
	}

	public void deleteVotoById(int id) {
		dao.deleteVotoById(id);
	}
	
	public List<Voto> findAllVotos() {
		return dao.findAllVotos();
	}
	
}
