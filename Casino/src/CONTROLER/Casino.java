/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CONTROLER;

import CONEXION.Connexio;
import static CONEXION.Queries.usuariAaraylist;
import MODEL.Usuari;
import VIEW.frmBlackjack;
import VIEW.frmLogIn;
import VIEW.frmMenu;
import java.util.ArrayList;

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
    public static String password = "Nico1234";
    public static String rutaFitxerLogActual;
    public static ArrayList<Usuari> Users = new ArrayList<Usuari>();
    public static Usuari userActual;


    public static void main(String[] args) {

        usuariAaraylist();
        GestioLog.creacioLog();

        Connexio c = new Connexio();
        c.connectar();

           /*frmBlackjack f = new frmBlackjack();
                f.setVisible(true);*/
        
        frmLogIn f = new frmLogIn();
        f.setVisible(true);
        for (Usuari u : Users) {
            System.out.println(u);
        }
        
    }

}
