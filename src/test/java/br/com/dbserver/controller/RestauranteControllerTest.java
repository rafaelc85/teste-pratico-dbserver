package br.com.dbserver.controller;

import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.Voto;
import br.com.dbserver.service.RestauranteService;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class RestauranteControllerTest {

	@Mock
	RestauranteService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	RestauranteController restauranteController;
	
	@Spy
	List<Restaurante> restaurantes = new ArrayList<Restaurante>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		restaurantes = getRestaurantesList();
	}
	
	@Test
	public void newRestaurante(){
		Assert.assertEquals(restauranteController.newRestaurante(model), "newRestaurante");
		Assert.assertNotNull(model.get("restaurante"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((Restaurante)model.get("restaurante")).getId(), 0);
	}

	@Test
	public void saveRestauranteWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveRestaurante(any(Restaurante.class));
		Assert.assertEquals(restauranteController.saveRestaurante(restaurantes.get(0), result, model), "/restaurante/new");
	}
	
	@Test
	public void saveRestauranteWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).saveRestaurante(any(Restaurante.class));
		Assert.assertEquals(restauranteController.saveRestaurante(restaurantes.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Restaurante Restaurante1 cadastrado com sucesso");
	}

	@Test
	public void editRestaurante(){
		Restaurante func = restaurantes.get(0);
		when(service.findById(anyInt())).thenReturn(func);
		Assert.assertEquals(restauranteController.editRestaurante(anyInt(), model), "newRestaurante");
		Assert.assertNotNull(model.get("restaurante"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((Restaurante)model.get("restaurante")).getId(), 1);
	}

	@Test
	public void updateRestauranteWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateRestaurante(any(Restaurante.class));
		Assert.assertEquals(restauranteController.updateRestaurante(restaurantes.get(0), result, model,1), "/restaurante/new");
	}

	@Test
	public void updateRestauranteWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).updateRestaurante(any(Restaurante.class));
		Assert.assertEquals(restauranteController.updateRestaurante(restaurantes.get(0), result, model, 1), "success");
		Assert.assertEquals(model.get("success"), "Restaurante Restaurante1 editado com suceesso");
	}	
	
	@Test
	public void deleteRestaurante(){
		doNothing().when(service).deleteRestauranteById(anyInt());
		Assert.assertEquals(restauranteController.deleteRestaurante(1), "redirect:/list");
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
