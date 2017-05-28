/*
 */

package br.com.dbserver.model;

/**
 *
 * Classe para facilitar a contagem de votos
 */
public class ContaVoto {
    Restaurante restaurante;
    int count;

    public ContaVoto(Restaurante restaurante, int count){
        this.restaurante = restaurante;
        this.count = count;
    }
    public int getRestauranteId(){
        return this.restaurante.getId();
    }
    public Restaurante getRestaurante(){
        return this.restaurante;
    }
    public int getCount(){
        return this.count;
    }
    public void incrementCount(){
        this.count++;
    }
}
