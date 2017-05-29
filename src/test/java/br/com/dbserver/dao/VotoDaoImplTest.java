package br.com.dbserver.dao;


import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.dbserver.model.Voto;
import br.com.dbserver.model.Voto;
import java.util.ArrayList;
import java.util.List;
import org.dbunit.dataset.CompositeDataSet;
import org.joda.time.LocalDate;


public class VotoDaoImplTest extends EntityDaoImplTest{

	@Autowired
	VotoDao votoDao;

	
        @Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Voto.xml"));
		return dataSet;
	}

	@Test
	public void findById(){
		Assert.assertNotNull(votoDao.findById(1));
		Assert.assertNull(votoDao.findById(3));
	}

	@Test
	public void findAllVotos(){
		Assert.assertEquals(votoDao.findAllVotos().size(), 2);
	}
	
	public Voto getSampleVoto(){
                Funcionario f1 = new Funcionario();
                f1.setId(1);
                f1.setNome("Axel");
                f1.setFuncao("Caixa");

                Restaurante r1 = new Restaurante();
                r1.setId(1);
                r1.setNome("Restaurante1");         

                return new Voto(1,f1,r1,new LocalDate(2017,06,02));
	}        
      

}
