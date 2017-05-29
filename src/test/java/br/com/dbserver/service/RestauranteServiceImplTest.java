package br.com.dbserver.service;

import br.com.dbserver.dao.RestauranteDao;
import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.Voto;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class RestauranteServiceImplTest {

	@Mock
	RestauranteDao dao;
	
	@InjectMocks
	RestauranteServiceImpl restauranteService;
	
	@Spy
	List<Restaurante> restaurantes = new ArrayList<Restaurante>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		restaurantes = getRestaurantesList();
	}

	@Test
	public void findById(){
		Restaurante emp = restaurantes.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(restauranteService.findById(emp.getId()),emp);
	}

	@Test
	public void saveRestaurante(){
		doNothing().when(dao).saveRestaurante(any(Restaurante.class));
		restauranteService.saveRestaurante(any(Restaurante.class));
		verify(dao, atLeastOnce()).saveRestaurante(any(Restaurante.class));
	}
	
	@Test
	public void updateRestaurante(){
		Restaurante emp = restaurantes.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		restauranteService.updateRestaurante(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}
	
	@Test
	public void findAllRestaurantes(){
		when(dao.findAllRestaurantes()).thenReturn(restaurantes);
		Assert.assertEquals(restauranteService.findAllRestaurantes(), restaurantes);
	}	
	
	public List<Restaurante> getRestaurantesList(){
		Funcionario f1 = new Funcionario();
		f1.setId(1);
		f1.setNome("Axel");
		f1.setFuncao("Caixa");
		
		Funcionario f2 = new Funcionario();
		f2.setId(2);
		f2.setNome("Jeremy");
		f2.setFuncao("Gerente");
            
                Restaurante r1 = new Restaurante();
		r1.setId(1);
		r1.setNome("Restaurante1");
                
                Restaurante r2 = new Restaurante();
		r2.setId(2);
		r2.setNome("Restaurante2");
                
                List<Voto> votos = new ArrayList<Voto>();                
                votos.add(new Voto(1,f1,r2,new LocalDate(2017,06,02)));
                votos.add(new Voto(2,f2,r1,new LocalDate(2017,03,25)));
                r1.setVotos(votos);
		votos.clear();
                votos.add(new Voto(1,f2,r1,new LocalDate(2017,06,02)));
                votos.add(new Voto(2,f1,r2,new LocalDate(2017,03,25)));		
		r2.setVotos(votos);
		
		restaurantes.add(r1);
		restaurantes.add(r2);
		return restaurantes;
	}
	
}
