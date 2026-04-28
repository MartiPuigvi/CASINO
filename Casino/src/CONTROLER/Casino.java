/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CONTROLER;

import CONEXION.Connexio;
import static CONEXION.Queries.adminAarrayList;
import static CONEXION.Queries.usuariAaraylist;
import MODEL.Admin;
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
    public static String password = "Marti";
    public static String rutaFitxerLogActual;
    public static ArrayList<Usuari> Users = new ArrayList<Usuari>();
    public static ArrayList<Admin> Admins = new ArrayList<Admin>();
    public static Usuari userActual;
   public static Admin adminActual;

    public static void main(String[] args) {

        usuariAaraylist();
        adminAarrayList();
        GestioLog.creacioLog();
        GestioLog.escriureLog("Programa iniciat");
        
        Connexio c = new Connexio();
        c.connectar();

        
        frmLogIn f = new frmLogIn();
        f.setVisible(true);
        for (Usuari u : Users) {
            System.out.println(u);
        }
        
         for (Admin s : Admins) {
            System.out.println(s);
        }
        
    }

}
