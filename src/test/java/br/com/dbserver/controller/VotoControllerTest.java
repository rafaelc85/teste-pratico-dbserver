package br.com.dbserver.controller;

import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.Voto;
import br.com.dbserver.service.VotoService;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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


public class VotoControllerTest {

	@Mock
	VotoService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	VotoController votoController;
	
	@Spy
	List<Voto> votos = new ArrayList<Voto>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
        
        @Mock
	Map<String, Object> map;
        
        @Mock
        HttpServletRequest request;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		votos = getVotosList();
	}
	
	@Test
	public void saveVotoWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveVoto(any(Voto.class));
		Assert.assertEquals(votoController.saveVoto(votos.get(0), map, result, model, request), "success");
	}
	
	@Test
	public void saveVotoWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).saveVoto(any(Voto.class));
		Assert.assertEquals(votoController.saveVoto(votos.get(0), map, result, model, request), "success");
		Assert.assertEquals(model.get("success"), "Voto registrado com sucesso");
	}	
	
	@Test
	public void deleteVoto(){
		doNothing().when(service).deleteVotoById(anyInt());
		Assert.assertEquals(votoController.deleteVoto(1), "redirect:/list");
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
}
