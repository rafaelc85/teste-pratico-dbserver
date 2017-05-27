package br.com.dbserver.service;

import br.com.dbserver.model.Funcionario;
import java.util.List;

import br.com.dbserver.model.Voto;

public interface VotoService {

	Voto findById(int id);
	
	void saveVoto(Voto voto);
	
	void updateVoto(Voto voto);
	
	void deleteVotoById(int id);

	List<Voto> findAllVotos();
        
        String validaVoto(Voto voto, Funcionario funcionario);
	
}
