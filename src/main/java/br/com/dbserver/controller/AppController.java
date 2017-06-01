package br.com.dbserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.RestauranteDia;
import br.com.dbserver.model.Voto;
import br.com.dbserver.service.FuncionarioService;
import br.com.dbserver.service.RestauranteDiaService;
import br.com.dbserver.service.RestauranteService;
import br.com.dbserver.service.VotoService;
import java.util.ArrayList;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/")
public class AppController {
        
        @Autowired
	FuncionarioService funcionarioService;
        
        @Autowired
	RestauranteService restauranteService;
        
        @Autowired
	VotoService votoService;
        
        @Autowired
	RestauranteDiaService restauranteDiaService;
	
	@Autowired
	MessageSource messageSource;
        
        LocalDate data = new LocalDate(); 
        //LocalDate data = new LocalDate(2017,01,05);

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String home(ModelMap model) {
		         
                List<Funcionario> funcionarios = funcionarioService.findAllFuncionarios();
		model.addAttribute("funcionarios", funcionarios);
                
                List<Restaurante> restaurantes = restauranteService.findAllRestaurantes();
		model.addAttribute("restaurantes", restaurantes);
                
                List<Voto> votos = votoService.findAllVotos();
		model.addAttribute("votos", votos);
		
                List<Voto> votosDia = votoService.findVotosByDate(new LocalDate());
		model.addAttribute("votosDia", votosDia);
                
                List<RestauranteDia> restaurantesDia = restauranteDiaService.findAllRestaurantesDia();
		model.addAttribute("restaurantesDia", restaurantesDia);                         
                
                RestauranteDia r = restauranteDiaService.findByData(data);
                Restaurante restauranteDoDia;
                
                //se o restaurante ja foi escolhido e armazenado no banco
                if(r!=null) {
                    restauranteDoDia = r.getRestaurante();
                    model.addAttribute("msgRestauranteDoDia", 
                            "O restaurante do dia é: " + restauranteDoDia.getNome());
                }
                else{
                    restauranteDoDia = votoService.selecionarRestauranteDia(data);                         
                                                           
                    //se nao foi possivel selecionar um restaurante
                    if(restauranteDoDia==null) 
                        model.addAttribute("msgRestauranteDoDia", "Nenhum voto valido ainda para o dia atual");
                    else{ 
                        model.addAttribute("msgRestauranteDoDia", 
                                "Restaurante do dia ainda não escolhido");
                        model.addAttribute("sorteio", true);
                        model.addAttribute("restauranteDoDia", restauranteDoDia);                 
                    }
                }
                
                return "home";
	}
        
        @RequestMapping(value = { "/sorteio/{id}" }, method = RequestMethod.GET)
	public String salvarRestauranteDia(@PathVariable int id) {               
                restauranteDiaService.saveRestauranteDia(
                        new RestauranteDia(restauranteService.findById(id), data));
		return "redirect:/list";
	}
        
        

}
