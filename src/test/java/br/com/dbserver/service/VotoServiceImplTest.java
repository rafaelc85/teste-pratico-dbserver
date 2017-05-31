package br.com.dbserver.service;

import br.com.dbserver.dao.VotoDao;
import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.Voto;
import br.com.dbserver.service.VotoServiceImpl;
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


public class VotoServiceImplTest {

	@Mock
	VotoDao dao;
	
	@InjectMocks
	VotoServiceImpl votoService;
	
	@Spy
	List<Voto> votos = new ArrayList<Voto>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		votos = getVotosList();
	}
        
        
        @Test
	public void validaRegra1WhenAlreadyVoted(){
                when(dao.findVotosByFuncionario(any(Funcionario.class))).thenReturn(votos);
                Voto voto = votos.get(0);
                Assert.assertEquals(votoService.validaRegra1(voto), "Erro: funcionario ja deu seu voto para este dia");
	}
        
        @Test
	public void validaRegra1WhithSuccess(){
                when(dao.findVotosByFuncionario(any(Funcionario.class))).thenReturn(votos);
                Voto voto = new Voto();
                voto.setId(0);
                voto.setFuncionario(votos.get(0).getFuncionario());
                voto.setData(new LocalDate(2017,07,22));		
		Assert.assertEquals(votoService.validaRegra1(voto), null);
	}   
        
        @Test
	public void validaRegra1WhenVotoNull(){
                Voto voto = null;		
		Assert.assertEquals(votoService.validaRegra1(voto), "Voto vazio");
	}      

	@Test
	public void findById(){
		Voto emp = votos.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(votoService.findById(emp.getId()),emp);
	}
        
	@Test
	public void saveVoto(){
		doNothing().when(dao).saveVoto(any(Voto.class));
		votoService.saveVoto(any(Voto.class));
		verify(dao, atLeastOnce()).saveVoto(any(Voto.class));
	}
	
	@Test
	public void updateVoto(){
		Voto emp = votos.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		votoService.updateVoto(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}
        
	
	@Test
	public void findAllVotos(){
		when(dao.findAllVotos()).thenReturn(votos);
		Assert.assertEquals(votoService.findAllVotos(), votos);
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