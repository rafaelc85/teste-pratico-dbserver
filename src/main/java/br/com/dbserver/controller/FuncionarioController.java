package br.com.dbserver.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.dbserver.model.Funcionario;
import br.com.dbserver.service.FuncionarioService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/")
public class FuncionarioController {
       
        @Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	MessageSource messageSource;
        
        @RequestMapping(value = { "/funcionario/new" }, method = RequestMethod.GET)
	public String newFuncionario(ModelMap model) {
		Funcionario funcionario = new Funcionario();
		model.addAttribute("funcionario", funcionario);
		model.addAttribute("edit", false);
		return "newFuncionario";
	}
        
        @RequestMapping(value = { "/funcionario/new" }, method = RequestMethod.POST)
	public String saveFuncionario(@Valid Funcionario funcionario, BindingResult result,
			ModelMap model) {
            
		if (result.hasErrors()) {
			return "newFuncionario";
		}	
		funcionarioService.saveFuncionario(funcionario);
		model.addAttribute("success", "Funcionario " + funcionario.getNome() + " cadastrado com sucesso");
		return "success";
	}
        
	@RequestMapping(value = { "/funcionario/edit/{id}" }, method = RequestMethod.GET)
	public String editFuncionario(@PathVariable int id, ModelMap model) {
		Funcionario funcionario = funcionarioService.findById(id);
		model.addAttribute("funcionario", funcionario);
		model.addAttribute("edit", true);
		return "newFuncionario";
	}
        
	@RequestMapping(value = { "/funcionario/edit/{id}" }, method = RequestMethod.POST)
	public String updateFuncionario(@Valid Funcionario funcionario, BindingResult result,
			ModelMap model, @PathVariable int id) {

		if (result.hasErrors()) {
			return "newFuncionario";
		}
		funcionarioService.updateFuncionario(funcionario);
		model.addAttribute("success", "Funcionario " + funcionario.getNome()+ " editado com suceesso");
		return "success";
	}

	@RequestMapping(value = { "/funcionario/delete/{id}" }, method = RequestMethod.GET)
	public String deleteFuncionario(@PathVariable int id) {
		funcionarioService.deleteFuncionarioById(id);
		return "redirect:/list";
	}
 
}
