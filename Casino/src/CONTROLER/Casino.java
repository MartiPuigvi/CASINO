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
import VIEW.frmTragaperras;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.UIManager;

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
    public static ArrayList<Admin> Admins = new ArrayList<Admin>();
    public static Usuari userActual;
    public static Admin adminActual;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());

            UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("Button.arc", 999);
            UIManager.put("Component.arc", 12);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
