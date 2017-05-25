package br.com.dbserver.dao;

import java.util.List;

import br.com.dbserver.model.Funcionario;

public interface FuncionarioDao {

	Funcionario findById(int id);

	void saveFuncionario(Funcionario funcionario);
	
	void deleteFuncionarioById(int id);
	
	List<Funcionario> findAllFuncionarios();

}
