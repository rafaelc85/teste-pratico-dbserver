package br.com.dbserver.service;

import java.util.List;

import br.com.dbserver.model.Funcionario;

public interface FuncionarioService {

	Funcionario findById(int id);
	
	void saveFuncionario(Funcionario funcionario);
	
	void updateFuncionario(Funcionario funcionario);
	
	void deleteFuncionarioById(int id);

	List<Funcionario> findAllFuncionarios(); 
	
}
