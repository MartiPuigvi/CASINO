/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.ArrayList;

/**
 *
 * @author nicod
 */
public class jocBlackjack extends Joc {
    
    private ArrayList<Integer> cartasJugador;
    private ArrayList<Integer> cartasDealer;
    private double aposta;

    public jocBlackjack(ArrayList<Integer> cartasJugador, ArrayList<Integer> cartasDealer, double aposta, String nom) {
        super(nom);
        this.cartasJugador = cartasJugador;
        this.cartasDealer = cartasDealer;
        this.aposta = aposta;
    }
    
    public ArrayList<Integer> getCartasJugador() {
        return cartasJugador;
    }

    public void setCartasJugador(ArrayList<Integer> cartasJugador) {
        this.cartasJugador = cartasJugador;
    }

    public ArrayList<Integer> getCartasDealer() {
        return cartasDealer;
    }

    public void setCartasDealer(ArrayList<Integer> cartasDealer) {
        this.cartasDealer = cartasDealer;
    }

    public double getAposta() {
        return aposta;
    }

    public void setAposta(double aposta) {
        this.aposta = aposta;
    }

    @Override
    public String toString() {
        return "jocBlackjack{" + "cartasJugador=" + cartasJugador + ", cartasDealer=" + cartasDealer + ", aposta=" + aposta + '}';
    }
    
    
}
