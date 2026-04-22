/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**´?.l.,p
 *
 * @author nicod
 */
public class jocTrragaperras extends Joc {
    
    private String simbol1;
    private String simbol2;
    private String simbol3;
    private double aposta;

    public jocTrragaperras(String simbol1, String simbol2, String simbol3, double aposta, String nom) {
        super(nom);
        this.simbol1 = simbol1;
        this.simbol2 = simbol2;
        this.simbol3 = simbol3;
        this.aposta = aposta;
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

    public double getAposta() {
        return aposta;
    }

    public void setAposta(double aposta) {
        this.aposta = aposta;
    }

    @Override
    public String toString() {
        return "jocTrragaperras{" + "simbol1=" + simbol1 + ", simbol2=" + simbol2 + ", simbol3=" + simbol3 + ", aposta=" + aposta + '}';
    }
    
    

}
