/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author nicod
 */
public class jocRuleta extends Joc {
    
    private int numeroGuanyador;
    private String color;
    private double aposta;

    public jocRuleta(int numeroGuanyador, String color, double aposta, String nom) {
        super(nom);
        this.numeroGuanyador = numeroGuanyador;
        this.color = color;
        this.aposta = aposta;
    }



    public int getNumeroGuanyador() {
        return numeroGuanyador;
    }

    public void setNumeroGuanyador(int numeroGuanyador) {
        this.numeroGuanyador = numeroGuanyador;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getAposta() {
        return aposta;
    }

    public void setAposta(double aposta) {
        this.aposta = aposta;
    }

    @Override
    public String toString() {
        return "jocRuleta{" + "numeroGuanyador=" + numeroGuanyador + ", color=" + color + ", aposta=" + aposta + '}';
    }
   

}
