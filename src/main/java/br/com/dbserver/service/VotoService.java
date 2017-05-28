package br.com.dbserver.service;

import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.RestauranteDia;
import java.util.List;

import br.com.dbserver.model.Voto;
import org.joda.time.LocalDate;

public interface VotoService {

	Voto findById(int id);
	
	void saveVoto(Voto voto);
	
	void updateVoto(Voto voto);
	
	void deleteVotoById(int id);

	List<Voto> findAllVotos();
        
        String validaVoto(Voto voto, Funcionario funcionario);
        
        List<Voto> findVotosByDate(LocalDate data);
        
        Restaurante selecionarRestauranteDia(LocalDate data, List<RestauranteDia> restaurantesDia);
	
}
