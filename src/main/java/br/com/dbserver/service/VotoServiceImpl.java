package br.com.dbserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbserver.dao.VotoDao;
import br.com.dbserver.model.Funcionario;
import br.com.dbserver.model.Restaurante;
import br.com.dbserver.model.Voto;
import java.util.Date;
import org.joda.time.DateTime;
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
            regra 2 - mesmo restaurante só pode ser escolhido uma vez na semana por funcionario 
            se retornar null é pq o voto é valido, caso contrário retorna a mensagem especificando o erro */        
        public String validaVoto(Voto voto, Funcionario funcionario) {
            if (voto==null || funcionario==null) return "O voto e o funcionario devem ser especificados";
            List<Voto> votos;
            votos = dao.findVotosByFuncionario(funcionario);
            for(int i=0;i<votos.size();i++){
                //regra 1
                if(votos.get(i).getData().equals(voto.getData())) return "funcionario ja deu seu voto para este dia";
                java.util.Date d1,d2;
                d1 = votos.get(i).getData().toDateTimeAtStartOfDay().toDate();
                d2 = voto.getData().toDateTimeAtStartOfDay().toDate();
                //regra 2
                if( votos.get(i).getRestaurante().getId() == voto.getRestaurante().getId() && 
                        isSameWeek(d1,d2)) 
                    return "funcionario já votou neste restaurante esta semana";
            }
            return null;
        }
               
        //retorna true se as datas estiverem na mesma semana ou falso caso contrario
        public static boolean isSameWeek(final Date d1, final Date d2) {
            if ((d1 == null) || (d2 == null))
                throw new IllegalArgumentException("Valor da Data nao pode ser null");

            return isSameWeek(new DateTime(d1), new DateTime(d2));
        }
        public static boolean isSameWeek(final DateTime d1, final DateTime d2) {
            if ((d1 == null) || (d2 == null))
                throw new IllegalArgumentException("Valor da Data nao pode ser null");

            final int week1 = d1.getWeekOfWeekyear();
            final int week2 = d2.getWeekOfWeekyear();

            final int year1 = d1.getWeekyear();
            final int year2 = d2.getWeekyear();

            final int era1 = d1.getEra();
            final int era2 = d2.getEra();

            if ((week1 == week2) && (year1 == year2) && (era1 == era2))
                return true;

            return false;
        }

        public Restaurante getRestauranteDia(LocalDate data) {
            Restaurante restauranteDia;
            List<Voto> votosDia = dao.findVotosByDate(data);
            for(int i=0;i<votosDia.size();i++){
                
            }                    
            return null;
        }

   
	
}
