/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;


import static CONTROLER.Casino.Admins;
import static CONTROLER.Casino.Users;
import static CONTROLER.Casino.adminActual;
import static CONTROLER.Casino.userActual;
import VIEW.frmMenu;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Marti Puigvi Serra
 */
public class GestioFitxers {
    
    /**
     * Crea un fitxer físic al disc si aquest no existeix.
     * @param text 
     */

    public static void creacioFitxer(String text) {

        try {

            File myObj = new File(text);
            if (myObj.createNewFile()) {
                System.out.println("Fitxer creat: " + myObj.getName());
            }

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();

        }
    }
    
    /**
     * Valida l'administrador actual i torna a obrir el menú principal en mode admin.
     */

    public static void tornarMenuAdmin() {
        for (int i = 0; i < CONTROLER.Casino.Admins.size(); i++) {
            if (Admins.get(i).getNom().equals(adminActual.getNom())
                    && Admins.get(i).getContrasenya().equals(adminActual.getContrasenya())) {

                frmMenu f = new frmMenu(true);
                f.setVisible(true);
                return;
            }
        }

    }

    /**
     * Valida l'usuari actual i torna a obrir el menú principal en mode client.
     */
    
    public static void tornarMenuClient() {

        for (int i = 0; i < Users.size(); i++) {
            if (Users.get(i).getNom().equals(userActual.getNom())
                    && Users.get(i).getContrasenya().equals(userActual.getContrasenya())) {

                frmMenu f = new frmMenu(false);
                f.setVisible(true);
                break;

            }

        }

    }   
}
