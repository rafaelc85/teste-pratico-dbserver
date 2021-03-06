package br.com.dbserver.dao;

import br.com.dbserver.model.Funcionario;
import java.util.List;

import br.com.dbserver.model.Voto;
import org.joda.time.LocalDate;

public interface VotoDao {

	Voto findById(int id);

	void saveVoto(Voto voto);
	
	void deleteVotoById(int id);
	
	List<Voto> findAllVotos();
        
        List<Voto> findVotosByFuncionario(Funcionario funcionario);
        
        List<Voto> findVotosByDate(LocalDate data);

}
