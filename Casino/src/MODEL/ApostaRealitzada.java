/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Marti Puigvi Serra
 */
public class ApostaRealitzada {

    public String tipus;
    public Object valor;
    public double diners;

    public ApostaRealitzada(String tipus, Object valor, double diners) {
        this.tipus = tipus;
        this.valor = valor;
        this.diners = diners;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public double getDiners() {
        return diners;
    }

    public void setDiners(double diners) {
        this.diners = diners;
    }

    
}
