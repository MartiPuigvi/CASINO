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

    private ArrayList<Integer> cartesJugador = new ArrayList<>();
    private ArrayList<Integer> cartesDealer = new ArrayList<>();
    private double aposta;

    public jocBlackjack(double aposta, String nom) {
        super(nom);
        this.cartesJugador = cartesJugador;
        this.cartesDealer = cartesDealer;
        this.aposta = aposta;
    }

    public void cartaJugador(int carta) {
        cartesJugador.add(carta);
    }

    public void cartaDealer(int carta) {
        cartesDealer.add(carta);
    }

    public ArrayList<Integer> getCartesJugador() {
        return cartesJugador;
    }

    public void setCartesJugador(ArrayList<Integer> cartesJugador) {
        this.cartesJugador = cartesJugador;
    }

    public ArrayList<Integer> getCartesDealer() {
        return cartesDealer;
    }

    public void setCartesDealer(ArrayList<Integer> cartesDealer) {
        this.cartesDealer = cartesDealer;
    }

    public double getAposta() {
        return aposta;
    }

    public void setAposta(double aposta) {
        this.aposta = aposta;
    }

    @Override
    public String toString() {
        return "jocBlackjack{" + "cartesJugador=" + cartesJugador + ", cartesDealer=" + cartesDealer + ", aposta=" + aposta + '}';
    }

}
