/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.Random;

/**
 * ´?.l.,p
 *
 * @author nicod
 */
public class jocTrragaperras extends Joc {

    private String[] simbols = {"🍒", "🍋", "⭐", "💎"};
    private String s1;
    private String s2;
    private String s3;
    private double aposta;

    public jocTrragaperras(double aposta, String nom) {
        super(nom);
        this.aposta = aposta;
    }

    public String[] getSimbols() {
        return simbols;
    }

    public void setSimbols(String[] simbols) {
        this.simbols = simbols;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public double getAposta() {
        return aposta;
    }

    public void setAposta(double aposta) {
        this.aposta = aposta;
    }

    public void girar() {
        Random random = new Random();

        s1 = simbols[random.nextInt(simbols.length)];
        s2 = simbols[random.nextInt(simbols.length)];
        s3 = simbols[random.nextInt(simbols.length)];

        if (s1.equals(s2) && s2.equals(s3)) {

            if (Math.random() < 0.99) {
                do {
                    s3 = simbols[random.nextInt(simbols.length)];
                } while (s3.equals(s1));
            }
        }

        if (s1.equals(s2) || s1.equals(s3) || s2.equals(s3)) {
        if (Math.random() < 0.90) {

            do {
                s1 = simbols[random.nextInt(simbols.length)];
                s2 = simbols[random.nextInt(simbols.length)];
                s3 = simbols[random.nextInt(simbols.length)];
            } while (s1.equals(s2) || s1.equals(s3) || s2.equals(s3));
            }
        }
    }

    @Override
    public String toString() {
        return "jocTrragaperras{" + "simbols=" + simbols + ", s1=" + s1 + ", s2=" + s2 + ", s3=" + s3 + ", aposta=" + aposta + '}';
    }
    
    
    
    
    
    
}
