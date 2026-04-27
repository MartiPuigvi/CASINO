/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;

import static CONTROLER.Casino.rutaFitxerLogActual;
import static CONTROLER.GestioFitxers.creacioFitxer;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author nicod
 */
public class GestioLog {

    public static void creacioLog() {
        
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String diaActual = LocalDateTime.now().format(formater);
        String directori = ("src/LOG/");
        String rutaCompleta = directori + diaActual + ".log";
        Casino.rutaFitxerLogActual = rutaCompleta;
        creacioFitxer(rutaCompleta);
    }

    public static void escriureLog(String text) {

        try {
            FileWriter myWriter = new FileWriter(rutaFitxerLogActual, true);

            DateTimeFormatter formater = DateTimeFormatter.ofPattern("hh:mm:ss");
            String instant = LocalDateTime.now().format(formater);

            myWriter.write(instant + "\t" + text + "\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Error d'escriptura");
            e.printStackTrace();

        }
    }
}
