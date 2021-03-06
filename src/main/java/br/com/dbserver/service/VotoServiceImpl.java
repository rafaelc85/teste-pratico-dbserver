package br.com.dbserver.service;

import br.com.dbserver.configuration.Util;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbserver.dao.VotoDao;
import br.com.dbserver.model.ContaVoto;
import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.RestauranteDia;
import br.com.dbserver.model.Voto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.joda.time.LocalDate;

@Service("votoService")
@Transactional
public class VotoServiceImpl implements VotoService {

	@Autowired
	private VotoDao dao;
	
	public Voto findById(int id) {
		return dao.findById(id);
	}

	public void saveVoto(Voto voto) {
		dao.saveVoto(voto);
	}

	public void updateVoto(Voto voto) {
            Voto entity = dao.findById(voto.getId());
            if(entity!=null){
                    entity.setData(voto.getData());;
                    entity.setFuncionario(voto.getFuncionario());
                    entity.setRestaurante(voto.getRestaurante());
            }
	}

	public void deleteVotoById(int id) {
		dao.deleteVotoById(id);
	}
	
	public List<Voto> findAllVotos() {
		return dao.findAllVotos();
	}
        
        public List<Voto> findVotosByDate(LocalDate data) {
            return dao.findVotosByDate(data);
        }
        
        /*  regra 1 - apenas um voto por dia por funcionario
          * se retornar null � pq o voto � valido, caso contr�rio retorna a mensagem especificando o erro */        
        public String validaRegra1(Voto voto) {
            if(voto==null) return "Voto vazio";
            List<Voto> votos;
            votos = dao.findVotosByFuncionario(voto.getFuncionario());
            
            if(votos.isEmpty()) return null;
            
            //Comparando as datas dos votos deste funcionario
            for(int i=0;i<votos.size();i++){
                //regra 1
                if(votos.get(i).getData().equals(voto.getData())) 
                    return "Erro: funcionario ja deu seu voto para este dia";                
            }
            return null;
        }
        
        /*  regra 2 - cada restaurante no maximo uma vez por semana
          * se retornar null � pq o restaurante ainda nao foi escolhido na semana, 
          * caso contr�rio retorna a mensagem especificando o erro */        
        public String validaRegra2(Voto voto, List<RestauranteDia> restaurantesDia) {
            Date d1, d2;
            d1 = voto.getData().toDateTimeAtStartOfDay().toDate();
            
            //Obtendo todas as escolhas de restaurantes da semana
            int k=0;
            while(k<restaurantesDia.size()){
                d2 = restaurantesDia.get(k).getData().toDateTimeAtStartOfDay().toDate();
                if(!Util.isSameWeek(d1, d2)) {
                    restaurantesDia.remove(k);                    
                } else { 
                    if(restaurantesDia.get(k).getRestaurante().getId() == (voto.getRestaurante().getId()))
                        return "Esse restaurante ja foi escolhido nesta semana";
                    k++;                   
                }
            }
            return null; 
        }
               
 
        //sorteia o restaurante do dia baseado nos votos recebidos
        public Restaurante selecionarRestauranteDia(LocalDate data) {  
            List<Voto> votosDia = dao.findVotosByDate(data);
            if(votosDia.isEmpty()) return null;
            
            //Contando os votos de cada restaurante no dia
            List<ContaVoto> contaVoto = new ArrayList<ContaVoto>();
            boolean contem = false;            
            for(int i=0;i<votosDia.size();i++){
                for(int j=0;j<contaVoto.size();j++){
                    if(votosDia.get(i).getRestaurante().getId() == contaVoto.get(j).getRestauranteId()){
                        contaVoto.get(j).incrementCount();
                        contem = true;
                    }
                }
                if(!contem) 
                    contaVoto.add(new ContaVoto(votosDia.get(i).getRestaurante(),1));                    
                else 
                    contem = false;
            } 
            
            //Selecionando o numero maximo de votos obtidos por um restaurante
            int maxVotos=0;
            for(int i=0;i<contaVoto.size();i++){
                if(contaVoto.get(i).getCount()>=maxVotos) maxVotos = contaVoto.get(i).getCount();
            }
            
            //Selecionando os restaurantes que possuem o numero maximo de votos
            List<Restaurante> restaurantesMaisVotados = new ArrayList<Restaurante>();
            for(int i=0;i<contaVoto.size();i++){
                if(contaVoto.get(i).getCount() == maxVotos) 
                    restaurantesMaisVotados.add(contaVoto.get(i).getRestaurante());
            }
            
            //Sorteando um dos restaurantes que tem mais votos e obedece os criterios
            Collections.shuffle(restaurantesMaisVotados);
             
            try{
                return restaurantesMaisVotados.get(0);
            }catch (Exception e){
                return null;
            }
            
            
                     
        }
           

   
	
}
