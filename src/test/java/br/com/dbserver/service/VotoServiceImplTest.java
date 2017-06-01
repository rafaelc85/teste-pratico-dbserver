package br.com.dbserver.service;

import br.com.dbserver.dao.RestauranteDiaDao;
import br.com.dbserver.dao.VotoDao;
import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.RestauranteDia;
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


public class VotoServiceImplTest {

	@Mock
	VotoDao dao;
        
        @Mock
	RestauranteDiaDao restauranteDiaDao;
	
	@InjectMocks
	VotoServiceImpl votoService;
        
        @InjectMocks
	RestauranteDiaServiceImpl restauranteDiaService;
	
	@Spy
	List<Voto> votos = new ArrayList<Voto>();
        
        @Spy
	List<RestauranteDia> rdList = new ArrayList<RestauranteDia>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		votos = getVotosList();
                rdList = getRestauranteDiaList();
	}
        
        
        @Test
	public void validaRegra1WhenAlreadyVoted(){
                when(dao.findVotosByFuncionario(any(Funcionario.class))).thenReturn(votos);
                Voto voto = votos.get(0);
                Assert.assertEquals(votoService.validaRegra1(voto), "Erro: funcionario ja deu seu voto para este dia");
	}
        
        @Test
	public void validaRegra1WithSuccess(){
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
	public void validaRegra2WhenRestauranteFoiEscolhidoNaSemana(){
                rdList = getRestauranteDiaList();
                when(restauranteDiaDao.findAllRestaurantesDia()).thenReturn(rdList);
                rdList.get(0).setData(new LocalDate(2017,05,31));
                Voto voto = votos.get(0);
                voto.setRestaurante(rdList.get(0).getRestaurante());
                voto.setData(new LocalDate(2017,06,01));
                Assert.assertEquals(votoService.validaRegra2(voto,rdList), "Esse restaurante ja foi escolhido nesta semana");
	}
        
        @Test
	public void validaRegra2WhenSemVotosNoDia(){
                Voto voto = votos.get(0);
                rdList.clear();
		Assert.assertEquals(votoService.validaRegra2(voto,rdList), null);
	}
        
        @Test
	public void validaRegra2WithSuccess(){
                rdList = getRestauranteDiaList();
                Voto voto = votos.get(0);
                Assert.assertEquals(votoService.validaRegra2(voto,rdList), null);
	}
        
        @Test
	public void selecionarRestauranteDiaWithSucess(){
                votos.get(0).setData(new LocalDate(2017,06,01));
                votos.get(1).setData(new LocalDate(2017,06,01));
                when(dao.findVotosByDate(new LocalDate(2017,06,01))).thenReturn(votos);
                Assert.assertNotNull(votoService.selecionarRestauranteDia(new LocalDate(2017,06,01)));
	}
        
        @Test
	public void selecionarRestauranteDiaSemVotosNoDia(){
                rdList = getRestauranteDiaList();
                when(dao.findVotosByDate(new LocalDate(2017,06,01))).thenReturn(votos);
                when(restauranteDiaDao.findAllRestaurantesDia()).thenReturn(rdList);
                Assert.assertEquals(votoService.selecionarRestauranteDia(new LocalDate(2020,06,01)), null);
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
        
        public List<RestauranteDia> getRestauranteDiaList(){                
                List<Restaurante> restaurantesList = getRestaurantes();  
                
                RestauranteDia rd1 = new RestauranteDia();
                rd1.setId(1);
                rd1.setData(new LocalDate(2017,05,15));
                rd1.setRestaurante(restaurantesList.get(0));
                
                RestauranteDia rd2 = new RestauranteDia();
                rd2.setId(2);
                rd2.setData(new LocalDate(2017,06,13));
                rd2.setRestaurante(restaurantesList.get(1));
                  
                rdList.add(rd1);
                rdList.add(rd2);                

		return rdList;                
                
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
                
                Funcionario f3 = new Funcionario();
		f3.setId(2);
		f3.setNome("Rafael");
		f3.setFuncao("Programador");
            
                List<Restaurante> restaurantesList = getRestaurantes();                
                               
                votos.add(new Voto(1,f1,restaurantesList.get(0),new LocalDate(2017,05,31)));
                votos.add(new Voto(2,f2,restaurantesList.get(0),new LocalDate(2017,05,31)));
                votos.add(new Voto(3,f3,restaurantesList.get(1),new LocalDate(2017,05,31)));
                votos.add(new Voto(4,f2,restaurantesList.get(1),new LocalDate(2017,03,25)));               

		return votos;
	}
        
        
        
        public List<Restaurante> getRestaurantes(){
                List<Restaurante> list = new ArrayList<Restaurante>();
            
		Restaurante r1 = new Restaurante();
		r1.setId(1);
		r1.setNome("Restaurante1");
                
                Restaurante r2 = new Restaurante();
		r2.setId(2);
		r2.setNome("Restaurante2");
                  
                list.add(r1);
                list.add(r2);

		return list;
	}
	
}