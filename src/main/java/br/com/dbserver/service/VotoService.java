package br.com.dbserver.service;

import java.util.List;

import br.com.dbserver.model.Voto;

public interface VotoService {

	Voto findById(int id);
	
	void saveVoto(Voto voto);
	
	void updateVoto(Voto voto);
	
	void deleteVotoById(int id);

	List<Voto> findAllVotos();
        
        
	
}
