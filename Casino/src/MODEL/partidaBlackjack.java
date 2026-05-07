/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Marti Puigvi Serra
 */
public class partidaBlackjack {
    private int idPartida;
    private String nomJugador;
    private int totalJugador;
    private int totalDealer;
    private boolean guanyador;

    public partidaBlackjack(int idPartida, String nomJugador, int totalJugador, int totalDealer, boolean guanyador) {
        this.idPartida = idPartida;
        this.nomJugador = nomJugador;
        this.totalJugador = totalJugador;
        this.totalDealer = totalDealer;
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

    public int getTotalJugador() {
        return totalJugador;
    }

    public void setTotalJugador(int totalJugador) {
        this.totalJugador = totalJugador;
    }

    public int getTotalDealer() {
        return totalDealer;
    }

    public void setTotalDealer(int totalDealer) {
        this.totalDealer = totalDealer;
    }

    public boolean isGuanyador() {
        return guanyador;
    }

    public void setGuanyador(boolean guanyador) {
        this.guanyador = guanyador;
    }

    @Override
    public String toString() {
        return "partidaBlackjack{" + "idPartida=" + idPartida + ", nomJugador=" + nomJugador + ", totalJugador=" + totalJugador + ", totalDealer=" + totalDealer + ", guanyador=" + guanyador + '}';
    }
    
    
}
