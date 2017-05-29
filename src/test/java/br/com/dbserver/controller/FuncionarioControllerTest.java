package br.com.dbserver.controller;

import br.com.dbserver.model.Funcionario;
import br.com.dbserver.service.FuncionarioService;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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


public class FuncionarioControllerTest {

	@Mock
	FuncionarioService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	FuncionarioController funcionarioController;
	
	@Spy
	List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		funcionarios = getFuncionariosList();
	}
	
	@Test
	public void newFuncionario(){
		Assert.assertEquals(funcionarioController.newFuncionario(model), "newFuncionario");
		Assert.assertNotNull(model.get("funcionario"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((Funcionario)model.get("funcionario")).getId(), 0);
	}

	@Test
	public void saveFuncionarioWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveFuncionario(any(Funcionario.class));
		Assert.assertEquals(funcionarioController.saveFuncionario(funcionarios.get(0), result, model), "/funcionario/new");
	}
	
	@Test
	public void saveFuncionarioWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).saveFuncionario(any(Funcionario.class));
		Assert.assertEquals(funcionarioController.saveFuncionario(funcionarios.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Funcionario Axel cadastrado com sucesso");
	}

	@Test
	public void editFuncionario(){
		Funcionario func = funcionarios.get(0);
		when(service.findById(anyInt())).thenReturn(func);
		Assert.assertEquals(funcionarioController.editFuncionario(anyInt(), model), "newFuncionario");
		Assert.assertNotNull(model.get("funcionario"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((Funcionario)model.get("funcionario")).getId(), 1);
	}

	@Test
	public void updateFuncionarioWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateFuncionario(any(Funcionario.class));
		Assert.assertEquals(funcionarioController.updateFuncionario(funcionarios.get(0), result, model,1), "/funcionario/new");
	}

	@Test
	public void updateFuncionarioWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).updateFuncionario(any(Funcionario.class));
		Assert.assertEquals(funcionarioController.updateFuncionario(funcionarios.get(0), result, model, 1), "success");
		Assert.assertEquals(model.get("success"), "Funcionario Axel editado com suceesso");
	}	
	
	@Test
	public void deleteFuncionario(){
		doNothing().when(service).deleteFuncionarioById(anyInt());
		Assert.assertEquals(funcionarioController.deleteFuncionario(1), "redirect:/list");
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
}
