package br.com.dbserver.service;

import br.com.dbserver.dao.FuncionarioDao;
import br.com.dbserver.model.Funcionario;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class FuncionarioServiceImplTest {

	@Mock
	FuncionarioDao dao;
	
	@InjectMocks
	FuncionarioServiceImpl funcionarioService;
	
	@Spy
	List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		funcionarios = getFuncionariosList();
	}

	@Test
	public void findById(){
		Funcionario emp = funcionarios.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(funcionarioService.findById(emp.getId()),emp);
	}

	@Test
	public void saveFuncionario(){
		doNothing().when(dao).saveFuncionario(any(Funcionario.class));
		funcionarioService.saveFuncionario(any(Funcionario.class));
		verify(dao, atLeastOnce()).saveFuncionario(any(Funcionario.class));
	}
	
	@Test
	public void updateFuncionario(){
		Funcionario emp = funcionarios.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		funcionarioService.updateFuncionario(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}
	
	@Test
	public void findAllFuncionarios(){
		when(dao.findAllFuncionarios()).thenReturn(funcionarios);
		Assert.assertEquals(funcionarioService.findAllFuncionarios(), funcionarios);
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
