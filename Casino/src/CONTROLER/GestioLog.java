/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;

import static CONTROLER.GestioFitxers.creacioFitxer;
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
}
