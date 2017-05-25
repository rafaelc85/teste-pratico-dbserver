package br.com.dbserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbserver.dao.FuncionarioDao;
import br.com.dbserver.model.Funcionario;

@Service("funcionarioService")
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao dao;
	
	public Funcionario findById(int id) {
		return dao.findById(id);
	}

	public void saveFuncionario(Funcionario funcionario) {
		dao.saveFuncionario(funcionario);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateFuncionario(Funcionario funcionario) {
		Funcionario entity = dao.findById(funcionario.getId());
		if(entity!=null){
			entity.setNome(funcionario.getNome());
                        entity.setFuncao(funcionario.getFuncao());
		}
	}

	public void deleteFuncionarioById(int id) {
		dao.deleteFuncionarioById(id);
	}
	
	public List<Funcionario> findAllFuncionarios() {
		return dao.findAllFuncionarios();
	}
	
}
