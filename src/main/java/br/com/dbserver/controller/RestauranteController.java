package br.com.dbserver.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.dbserver.model.Restaurante;
import br.com.dbserver.service.RestauranteService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/")
public class RestauranteController {
       
        @Autowired
	RestauranteService restauranteService;
	
	@Autowired
	MessageSource messageSource;
        
        @RequestMapping(value = { "/restaurante/new" }, method = RequestMethod.GET)
	public String newRestaurante(ModelMap model) {
		Restaurante restaurante = new Restaurante();
		model.addAttribute("restaurante", restaurante);
		model.addAttribute("edit", false);
		return "newRestaurante";
	}
        
        @RequestMapping(value = { "/restaurante/new" }, method = RequestMethod.POST)
	public String saveRestaurante(@Valid Restaurante restaurante, BindingResult result,
			ModelMap model) {
            
		if (result.hasErrors()) {
			return "/restaurante/new";
		}	
		restauranteService.saveRestaurante(restaurante);
		model.addAttribute("success", "Restaurante " + restaurante.getNome() + " cadastrado com sucesso");
		return "success";
	}
        
	@RequestMapping(value = { "/restaurante/edit/{id}" }, method = RequestMethod.GET)
	public String editFuncionario(@PathVariable int id, ModelMap model) {
		Restaurante restaurante = restauranteService.findById(id);
		model.addAttribute("restaurante", restaurante);
		model.addAttribute("edit", true);
		return "newRestaurante";
	}
        
	@RequestMapping(value = { "/restaurante/edit/{id}" }, method = RequestMethod.POST)
	public String updateFuncionario(@Valid Restaurante restaurante, BindingResult result,
			ModelMap model, @PathVariable int id) {

		if (result.hasErrors()) {
			return "/restaurante/new";
		}
		restauranteService.updateRestaurante(restaurante);
		model.addAttribute("success", "Restaurante " + restaurante.getNome()+ " editado com suceesso");
		return "success";
	}

	@RequestMapping(value = { "/restaurante/delete/{id}" }, method = RequestMethod.GET)
	public String deleteFuncionario(@PathVariable int id) {
		restauranteService.deleteRestauranteById(id);
		return "redirect:/list";
	}
 
}
