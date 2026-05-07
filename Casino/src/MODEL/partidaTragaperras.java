/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Marti Puigvi Serra
 */
public class partidaTragaperras {
    
    private int idPartida;
    private String nomJugador;
    private String simbol1;
    private String simbol2;
    private String simbol3;
    private boolean guanyador;

    public partidaTragaperras(int idPartida, String nomJugador, String simbol1, String simbol2, String simbol3, boolean guanyador) {
        this.idPartida = idPartida;
        this.nomJugador = nomJugador;
        this.simbol1 = simbol1;
        this.simbol2 = simbol2;
        this.simbol3 = simbol3;
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

    public String getSimbol1() {
        return simbol1;
    }

    public void setSimbol1(String simbol1) {
        this.simbol1 = simbol1;
    }

    public String getSimbol2() {
        return simbol2;
    }

    public void setSimbol2(String simbol2) {
        this.simbol2 = simbol2;
    }

    public String getSimbol3() {
        return simbol3;
    }

    public void setSimbol3(String simbol3) {
        this.simbol3 = simbol3;
    }

    public boolean isGuanyador() {
        return guanyador;
    }

    public void setGuanyador(boolean guanyador) {
        this.guanyador = guanyador;
    }

    @Override
    public String toString() {
        return "partidaTragaperras{" + "idPartida=" + idPartida + ", nomJugador=" + nomJugador + ", simbol1=" + simbol1 + ", simbol2=" + simbol2 + ", simbol3=" + simbol3 + ", guanyador=" + guanyador + '}';
    }
    
}
