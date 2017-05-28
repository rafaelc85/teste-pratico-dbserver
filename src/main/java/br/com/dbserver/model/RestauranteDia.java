package br.com.dbserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="restaurante_dia")
public class RestauranteDia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
    
    @NotNull
    @DateTimeFormat(pattern="MM/dd/yyyy") 
    @Column(name = "data", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate data;

    public RestauranteDia() { }    
    
    public RestauranteDia(Restaurante restaurante, LocalDate data){
        this.restaurante = restaurante;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestauranteDia{" + "id=" + id + ", restaurante=" + restaurante + ", data=" + data + '}';
    }
    
    
    

 
}
