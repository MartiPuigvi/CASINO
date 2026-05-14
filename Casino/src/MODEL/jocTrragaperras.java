/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import static CONTROLER.Casino.userActual;
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

        double r = Math.random();

        if (r < 0.017) {
            String s = simbols[random.nextInt(simbols.length)];
            s1 = s;
            s2 = s;
            s3 = s;
        } else if (r < 0.35) {

            int igual = random.nextInt(3);

            String sim = simbols[random.nextInt(simbols.length)];

            switch (igual) {

                case 0:
                    s1 = sim;
                    s2 = sim;
                    do {
                        s3 = simbols[random.nextInt(simbols.length)];
                    } while (s3.equals(sim));
                    break;

                case 1:
                    s2 = sim;
                    s3 = sim;
                    do {
                        s1 = simbols[random.nextInt(simbols.length)];
                    } while (s1.equals(sim));
                    break;

                case 2:
                    s1 = sim;
                    s3 = sim;
                    do {
                        s2 = simbols[random.nextInt(simbols.length)];
                    } while (s2.equals(sim));
                    break;
            }

        } else {
            s1 = simbols[random.nextInt(simbols.length)];

            do {
                s2 = simbols[random.nextInt(simbols.length)];
            } while (s2.equals(s1));

            do {
                s3 = simbols[random.nextInt(simbols.length)];
            } while (s3.equals(s1) || s3.equals(s2));
        }

    }

    @Override
    public String toString() {
        return "jocTrragaperras{" + "simbols=" + simbols + ", s1=" + s1 + ", s2=" + s2 + ", s3=" + s3 + ", aposta=" + aposta + '}';
    }

}
