package br.com.dbserver.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=3, max=50)
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @OneToMany(mappedBy = "restaurante")
    private List<Voto> votos = new ArrayList<Voto>();    
    
    public List<Voto> getVotos() {
        return this.votos;
    }
    
    public void setVotos(List<Voto> votos) {        
        this.votos = votos;
    }
    
    public void addVoto(Voto voto){
        this.votos.add(voto);
        if(voto.getRestaurante() != this){
            voto.setRestaurante(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", nome=" + nome + '}';
    }       
	

}
