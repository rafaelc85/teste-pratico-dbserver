package br.com.dbserver.controller;


import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.RestauranteDia;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.dbserver.model.Voto;
import br.com.dbserver.service.FuncionarioService;
import br.com.dbserver.service.RestauranteDiaService;
import br.com.dbserver.service.RestauranteService;
import br.com.dbserver.service.VotoService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class VotoController {
       
        @Autowired
	VotoService votoService;
        
        @Autowired
	FuncionarioService funcionarioService;
        
        @Autowired
	RestauranteService restauranteService;
        
        @Autowired
        RestauranteDiaService restauranteDiaService;
	
	@Autowired
	MessageSource messageSource;
        
               
        @RequestMapping(value = { "/voto/new" }, method = RequestMethod.GET)
	public String newVoto(ModelMap model) {
                try{
                    List<Funcionario> funcionarios = funcionarioService.findAllFuncionarios();
                    model.addAttribute("funcionarios", funcionarios);

                    List<Restaurante> restaurantes = restauranteService.findAllRestaurantes();
                    model.addAttribute("restaurantes", restaurantes);

                    Voto voto = new Voto();
                    model.addAttribute("voto", voto);
                    model.addAttribute("edit", false);
                    return "newVoto";
                    
                } catch (Exception e){
                    System.out.println("Erro ao adicionar o voto");
                }
                return "newVoto";
	}
        
        @RequestMapping(value = { "/voto/new" }, method = RequestMethod.POST)
	public String saveVoto(@ModelAttribute("voto") Voto voto, Map<String, Object> map,
                BindingResult result, ModelMap model,  HttpServletRequest request) {
                                                            
		if (result.hasErrors()) {
                        return "newVoto";
		}
                try{
                    int id;
                    id = (int)Integer.parseInt(request.getParameter("funcionario_id")); 
                    voto.setFuncionario(funcionarioService.findById(id));
                    id = (int)Integer.parseInt(request.getParameter("restaurante_id")); 
                    voto.setRestaurante(restauranteService.findById(id));
                }catch(Exception e){
                    System.out.println("Erro ao validar a identificacao do funcionario e do restaurante");
                }
                
                //Validando regra1
                String validaRegra1 = votoService.validaRegra1(voto); 
                if(validaRegra1!=null){
                    model.addAttribute("success", validaRegra1);
                    return "success";
                }
                
                //Validando regra2
                List<RestauranteDia> restaurantesDia = restauranteDiaService.findAllRestaurantesDia();
                String validaRegra2 = votoService.validaRegra2(voto, restaurantesDia);
                if(validaRegra2!=null){
                    model.addAttribute("success", validaRegra2);
                    return "success";
                }
                
		votoService.saveVoto(voto);
		model.addAttribute("success", "Voto registrado com sucesso");
		return "success";
	}
        
        @RequestMapping(value = { "/novoVoto" }, method = RequestMethod.POST)
        @ResponseBody
	public String novoVoto(HttpServletRequest request, ModelMap model,
                @RequestParam(value="id", required=false) int id, 
                @RequestParam(value="funcionario", required=false) int funcionario, 
                @RequestParam(value="restaurante", required=false) int restaurante,
                @RequestParam(value="data", required=false) String data){          
                                                                   
               
                Voto voto = new Voto();
                voto.setId(id);
                voto.setFuncionario(funcionarioService.findById(funcionario));
                voto.setRestaurante(restauranteService.findById(restaurante));
                voto.setData(LocalDate.parse(data));
                votoService.saveVoto(voto);
                model.addAttribute("success", "Voto registrado com sucesso");
                return "success";              
		
	}
        
	@RequestMapping(value = { "/voto/edit/{id}" }, method = RequestMethod.GET)
	public String editVoto(@PathVariable int id, ModelMap model) {
		Voto voto = votoService.findById(id);
		model.addAttribute("voto", voto);
		model.addAttribute("edit", true);
		return "newRestaurante";
	}
        
	@RequestMapping(value = { "/voto/edit/{id}" }, method = RequestMethod.POST)
	public String updateVoto(@Valid Voto voto, BindingResult result,
			ModelMap model, @PathVariable int id) {

		if (result.hasErrors()) {
			return "/restaurante/new";
		}
		votoService.updateVoto(voto);
		model.addAttribute("success", "Voto editado com suceesso");
		return "success";
	}

	@RequestMapping(value = { "/voto/delete/{id}" }, method = RequestMethod.GET)
	public String deleteVoto(@PathVariable int id) {
		votoService.deleteVotoById(id);
		return "redirect:/list";
	}
 
}
