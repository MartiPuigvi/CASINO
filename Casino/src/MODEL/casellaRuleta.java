/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Marti Puigvi Serra
 */
public class casellaRuleta {
    
    private int numero;
    private String color;
    private boolean parell;

    public casellaRuleta(int numero, String color, boolean parell) {
        this.numero = numero;
        this.color = color;
        this.parell = (numero != 0 && numero % 2 == 0);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isParell() {
        return parell;
    }

    public void setParell(boolean parell) {
        this.parell = parell;
    }

    @Override
    public String toString() {
        return "casellaRuleta{" + "numero=" + numero + ", color=" + color + ", parell=" + parell + '}';
    }  
    
}
