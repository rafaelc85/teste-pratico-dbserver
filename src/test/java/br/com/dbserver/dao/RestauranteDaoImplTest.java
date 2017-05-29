package br.com.dbserver.dao;


import br.com.dbserver.model.Funcionario;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.Voto;
import java.util.ArrayList;
import java.util.List;
import org.dbunit.dataset.CompositeDataSet;
import org.joda.time.LocalDate;


public class RestauranteDaoImplTest extends EntityDaoImplTest{

	@Autowired
	RestauranteDao restauranteDao;

	
        @Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Restaurante.xml"));
		return dataSet;
	}

	@Test
	public void findById(){
		Assert.assertNotNull(restauranteDao.findById(1));
		Assert.assertNull(restauranteDao.findById(3));
	}

	
	@Test
	public void saveRestaurante(){
		restauranteDao.saveRestaurante(getSampleRestaurante());
		Assert.assertEquals(restauranteDao.findAllRestaurantes().size(), 3);
	}	

	@Test
	public void findAllRestaurantes(){
		Assert.assertEquals(restauranteDao.findAllRestaurantes().size(), 2);
	}
	
	public Restaurante getSampleRestaurante(){
		Restaurante r1 = new Restaurante();
		r1.setNome("Restaurante1");
                
                Funcionario f1 = new Funcionario();
		f1.setId(1);
		f1.setNome("Axel");
		f1.setFuncao("Caixa");
                
                Funcionario f2 = new Funcionario();
		f2.setId(2);
		f2.setNome("Jeremy");
		f2.setFuncao("Gerente");
                
                List<Voto> votos = new ArrayList<Voto>();                
                votos.add(new Voto(1,f1,r1,new LocalDate(2017,06,02)));
                votos.add(new Voto(2,f2,r1,new LocalDate(2017,03,25)));
                
                r1.setVotos(votos);
                
		return r1;
	}        
      

}
