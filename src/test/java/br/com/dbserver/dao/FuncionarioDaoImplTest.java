package br.com.dbserver.dao;


import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.dbserver.model.Funcionario;


public class FuncionarioDaoImplTest extends EntityDaoImplTest{

	@Autowired
	FuncionarioDao funcionarioDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Funcionario.xml"));
		return dataSet;
	}

	@Test
	public void findById(){
		Assert.assertNotNull(funcionarioDao.findById(1));
		Assert.assertNull(funcionarioDao.findById(3));
	}

	
	@Test
	public void saveFuncionario(){
		funcionarioDao.saveFuncionario(getSampleFuncionario());
		Assert.assertEquals(funcionarioDao.findAllFuncionarios().size(), 3);
	}	

	@Test
	public void findAllFuncionarios(){
		Assert.assertEquals(funcionarioDao.findAllFuncionarios().size(), 2);
	}
	
	public Funcionario getSampleFuncionario(){
		Funcionario f1 = new Funcionario();
		f1.setNome("Rafael");
		f1.setFuncao("Programador");
		return f1;
	}

}
