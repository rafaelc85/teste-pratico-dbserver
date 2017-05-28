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
import br.com.dbserver.model.Voto;
import br.com.dbserver.service.FuncionarioService;
import br.com.dbserver.service.RestauranteService;
import br.com.dbserver.service.VotoService;
import org.joda.time.LocalDate;

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
	MessageSource messageSource;

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
                
                //Restaurante restauranteDia = 
                
                return "home";
	}

}
