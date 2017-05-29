package br.com.dbserver.controller;



import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.RestauranteDia;
import br.com.dbserver.model.Voto;
import br.com.dbserver.service.FuncionarioService;
import br.com.dbserver.service.RestauranteDiaService;
import br.com.dbserver.service.RestauranteService;
import br.com.dbserver.service.VotoService;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppControllerTest {
    
        @Mock
	FuncionarioService funcionarioService;
        
        @Mock
	RestauranteService restauranteService;
        
        @Mock
	VotoService votoService;
        
        @Mock
	RestauranteDiaService restauranteDiaService;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	AppController appController;
        
        @Spy
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        @Spy        
        List<Restaurante> restaurantes = new ArrayList<Restaurante>();

        @Spy
        List<Voto> votos = new ArrayList<Voto>();

        @Spy
        List<Voto> votosDia = new ArrayList<Voto>();

        @Spy
        List<RestauranteDia> restaurantesDia = new ArrayList<RestauranteDia>();     

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		funcionarios = getFuncionariosList();
                restaurantes = getRestaurantesList();
                votos = getVotosList();
                restaurantesDia = getRestaurantesDiaList();                              
	}
	
	@Test
	public void home(){
		when(funcionarioService.findAllFuncionarios()).thenReturn(funcionarios);
                when(restauranteService.findAllRestaurantes()).thenReturn(restaurantes);
                when(votoService.findAllVotos()).thenReturn(votos);
                when(restauranteDiaService.findAllRestaurantesDia()).thenReturn(restaurantesDia);
		Assert.assertEquals(appController.home(model), "home");
		Assert.assertEquals(model.get("funcionarios"), funcionarios);
                Assert.assertEquals(model.get("restaurantes"), restaurantes);
                Assert.assertEquals(model.get("votos"), votos);
                Assert.assertEquals(model.get("restaurantesDia"), restaurantesDia);
		verify(funcionarioService, atLeastOnce()).findAllFuncionarios();
                verify(restauranteService, atLeastOnce()).findAllRestaurantes();
                verify(votoService, atLeastOnce()).findAllVotos();
                verify(restauranteDiaService, atLeastOnce()).findAllRestaurantesDia();
	}
	
	@Test
	public void salvarRestauranteDia(){
		doNothing().when(restauranteDiaService).saveRestauranteDia(any(RestauranteDia.class));
		Assert.assertEquals(appController.salvarRestauranteDia(1), "redirect:/list");
	}

	public List<Funcionario> getFuncionariosList(){
		Funcionario f1 = new Funcionario();
		f1.setId(1);
		f1.setNome("Axel");
		f1.setFuncao("Caixa");
		
		Funcionario f2 = new Funcionario();
		f2.setId(2);
		f2.setNome("Jeremy");
		f2.setFuncao("Gerente");
		
		funcionarios.add(f1);
		funcionarios.add(f2);
		return funcionarios;
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
        
        public List<Voto> getVotosList(){
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
                               
                votos.add(new Voto(1,f1,r2,new LocalDate(2017,06,02)));
                votos.add(new Voto(2,f2,r1,new LocalDate(2017,03,25)));

		return votos;
	}
        
        public List<RestauranteDia> getRestaurantesDiaList(){          
                Restaurante r1 = new Restaurante();
		r1.setId(1);
		r1.setNome("Restaurante1");
                
                Restaurante r2 = new Restaurante();
		r2.setId(2);
		r2.setNome("Restaurante2");
                
                restaurantesDia.add(new RestauranteDia(r1,new LocalDate(2017,06,02)));
                restaurantesDia.add(new RestauranteDia(r2,new LocalDate(2017,03,25)));

		return restaurantesDia;
	}
}
