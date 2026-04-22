/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CONTROLER;

import CONEXION.Connexio;

/**
 *
 * @author Marti Puigvi Serra
 */
public class Casino {

    /**
     * @param args the command line arguments
     */
    public static String url = "jdbc:mysql://localhost:3306/casino";
    public static String user = "root";
    public static String password = "Nico";
    public static String rutaFitxerLogActual;

    public static void main(String[] args) {
        GestioLog.creacioLog();

        Connexio c = new Connexio();
        c.connectar();
    }

}
