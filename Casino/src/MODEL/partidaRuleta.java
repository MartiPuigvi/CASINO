/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Marti Puigvi Serra
 */
public class partidaRuleta {

    public enum colorRuleta {
        Vermell, Negre, Verd
    }

    private int idPartida;
    private String nomJugador;
    private int numero;
    private colorRuleta color;
    private boolean esParell;
    private String guanyador;

    public partidaRuleta(int idPartida, String nomJugador, int numero, colorRuleta color, boolean esParell, String guanyador) {
        this.idPartida = idPartida;
        this.nomJugador = nomJugador;
        this.numero = numero;
        this.color = color;
        this.esParell = esParell;
        this.guanyador = guanyador;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public String getNomJugador() {
        return nomJugador;
    }

    public void setNomJugador(String nomJugador) {
        this.nomJugador = nomJugador;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public colorRuleta getColor() {
        return color;
    }

    public void setColor(colorRuleta color) {
        this.color = color;
    }

    public boolean isEsParell() {
        return esParell;
    }

    public void setEsParell(boolean esParell) {
        this.esParell = esParell;
    }

    public String getGuanyador() {
        return guanyador;
    }

    public void setGuanyador(String guanyador) {
        this.guanyador = guanyador;
    }

    @Override
    public String toString() {
        return "partidaRuleta{" + "idPartida=" + idPartida + ", nomJugador=" + nomJugador + ", numero=" + numero + ", color=" + color + ", esParell=" + esParell + ", guanyador=" + guanyador + '}';
    }
}
