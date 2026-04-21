/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import CONTROLER.Casino;
import CONTROLER.GestioFitxers;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Marti Puigvi Serra
 */
public class GestioFitxers {

    public static void creacioLog() {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String diaActual = LocalDateTime.now().format(formater);
        String directori = ("src/LOG/");
        String rutaCompleta = directori + diaActual + ".log";
        Casino.rutaFitxerLogActual = rutaCompleta;
        creacioFitxer(rutaCompleta);
    }

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

}
